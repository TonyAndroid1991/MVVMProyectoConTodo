package com.talentomobile.marvel.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.talentomobile.marvel.R
import com.talentomobile.marvel.data.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val limit = 100

    companion object {
        private const val TAG = "HomeFragmentFragment"
    }

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel::class.java)

        getResponse()
    }

    private fun getResponse() {
        viewModel.getAllCharacters(limit)
        viewModel.getAllCharactersLiveData.observe(viewLifecycleOwner) { allCharactersResponse ->


            when (allCharactersResponse) {
                is Resource.Loading -> {
                    // show loading bar
                }

                is Resource.Success -> {
                    allCharactersResponse.data.let { allCharacters ->
                        // hide loading bar
                    }
                }

                is Resource.Error -> {
                    //hide loading bar
                    allCharactersResponse.message?.let {
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
}