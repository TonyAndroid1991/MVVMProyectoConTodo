package com.talentomobile.marvel.presentation.ui

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.talentomobile.marvel.R
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.databinding.FragmentHomeBinding
import com.talentomobile.marvel.presentation.ui.adapter.HomeRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        private const val TAG = "HomeFragmentFragment"
    }

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    @Inject
    lateinit var homeRecyclerAdapter: HomeRecyclerAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val BUNDLE_STRING_VALUE = "selected_character"
    private var isLoading = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        if (!viewModel.isOnline(requireContext())) {
            Toast.makeText(
                activity,
                resources.getString(R.string.error_no_internet_available),
                Toast.LENGTH_LONG
            ).show()
        }

        getResponse()

        homeRecyclerAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable(BUNDLE_STRING_VALUE, it)
            }
            this.findNavController().navigate(
                R.id.action_homeFragmentFragment_to_marvelCharacterDetailFragment,
                bundle
            )
        }

        binding.homeButton.setOnClickListener {
            getCharacterFromName()
        }
    }

    private fun getCharacterFromName() {
        val characterName = binding.inputText.text
        if (characterName.toString().isNotEmpty()) {
            viewModel.getCharacterFromName(characterName.toString())
            viewModel.getCharacterFromNameLiveData.observe(viewLifecycleOwner) { marvelCharacterEvent ->
                marvelCharacterEvent.getContentIfNotHandled().let { marvelCharacterResource ->
                    if (marvelCharacterResource != null) {
                        when (marvelCharacterResource) {
                            is Resource.Loading -> {
                                showProgressBar()
                                if (marvelCharacterResource.data?.id == null) {
                                    hideProgressBar()
                                    showToast(R.string.character_not_found.toString())
                                }
                            }

                            is Resource.Success -> {
                                hideProgressBar()
                                val marvelCharacter = marvelCharacterResource.data
                                val bundle = Bundle().apply {
                                    putSerializable(BUNDLE_STRING_VALUE, marvelCharacter)
                                }
                                this.findNavController().navigate(
                                    R.id.action_homeFragmentFragment_to_marvelCharacterDetailFragment,
                                    bundle
                                )
                            }
                            is Resource.Error -> {
                                showToast(R.string.error_no_data_available.toString())
                            }
                        }
                    }
                }
            }
        } else {
            Toast.makeText(activity, "You must input a name", Toast.LENGTH_LONG).show()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            activity,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun getResponse() {
        viewModel.getAllCharacters()
        viewModel.getAllCharactersLiveData.observe(viewLifecycleOwner) { allCharactersResponse ->

            when (allCharactersResponse) {
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Success -> {
                    allCharactersResponse.data?.let { allCharacters ->
                        hideProgressBar()
                        setRecyclerView(allCharacters)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    showToast(R.string.error_no_data_available.toString())
                }
            }
        }
    }

    private fun setRecyclerView(marvelCharacters: List<MarvelCharacter>) {
        binding.charactersRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            homeRecyclerAdapter.differ.submitList(marvelCharacters)
            adapter = homeRecyclerAdapter
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }
}