package com.talentomobile.marvel.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.talentomobile.marvel.R
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        private const val TAG = "HomeFragmentFragment"
    }

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        if(!viewModel.isOnline(requireContext())) {
            Toast.makeText(
                activity,
                resources.getString(R.string.error_no_internet_available),
                Toast.LENGTH_LONG
            ).show()
        }

        getResponse()
    }

    private fun setRecyclerView(marvelCharacters: List<MarvelCharacter>) {
     //   TODO("Not yet implemented")
    }

    private fun getResponse() {
        viewModel.getAllCharacters()
        viewModel.getAllCharactersLiveData.observe(viewLifecycleOwner) { allCharactersResponse ->

            when (allCharactersResponse) {
                is Resource.Loading -> {
                    // show loading bar
                }

                is Resource.Success -> {
                    allCharactersResponse.data?.let { allCharacters ->
                        // hide loading bar
                        Log.i(TAG, "getResponse: ${allCharacters.first().id}")
                        Log.i(TAG, "getResponse: ${allCharacters.first().name}")
                        Log.i(TAG, "getResponse: ${allCharacters.first().comics.items.first().name}")
                        setRecyclerView(allCharacters)
                    }
                }

                is Resource.Error -> {
                    //hide loading bar
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.error_no_data_available),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}