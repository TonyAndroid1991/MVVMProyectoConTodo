package com.talentomobile.marvel.presentation.ui

import android.os.Bundle
import android.util.Log
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

    val limit = 100

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

        /**TODO check if is better to do these verifications on viewmodel and here only get the info*/

        viewModel.getAllCharacters(limit)
        viewModel.getAllCharactersLiveData.observe(viewLifecycleOwner) { allCharactersResponse ->


            when (allCharactersResponse) {
                is Resource.Loading -> {
                    // show loading bar
                }

                is Resource.Success -> {
                    allCharactersResponse.data.let { allCharacters ->
                        // hide loading bar
                        allCharacters?.data?.let { data ->
                            Log.i(TAG, "getResponse: ${data.results[0].id} ===")
                            Log.i(TAG, "getResponse: ${data.results[0].name} ===")
                            Log.i(TAG, "getResponse: ${data.results.size} ===")
                        }
                    }
                }

                is Resource.Error -> {
                    //hide loading bar
                    allCharactersResponse.message?.let {
                        Toast.makeText(activity, "An error of type $it", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}