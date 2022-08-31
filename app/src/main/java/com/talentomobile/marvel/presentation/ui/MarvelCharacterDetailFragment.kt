package com.talentomobile.marvel.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.talentomobile.marvel.R
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Constants
import com.talentomobile.marvel.databinding.FragmentMarvelCharacterDetailBinding

class MarvelCharacterDetailFragment : Fragment() {

    private lateinit var viewModel: MarvelCharacterDetailViewModel
    private lateinit var binding: FragmentMarvelCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_marvel_character_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MarvelCharacterDetailViewModel::class.java)

        val args: MarvelCharacterDetailFragmentArgs by navArgs()
        val marvelCharacter = args.selectedCharacter

        populateFragment(marvelCharacter)
    }

    private fun populateFragment(marvelCharacter: MarvelCharacter) {
        val validImageUrl = marvelCharacter.thumbnail.path.replace(
            Constants.INVALID_URL_FORMAT,
            Constants.VALID_URL_FORMAT
        ).plus(".").plus(marvelCharacter.thumbnail.extension)

        binding.apply {
            characterDetailName.text = marvelCharacter.name
            characterDetailDescription.text = if (marvelCharacter.description.isNullOrBlank()) {
                getString(R.string.description).format(getString(R.string.no_description_was_found))
            } else {
                getString(R.string.description).format(marvelCharacter.description)
            }
            detailComics.text = marvelCharacter.comics.available.toString()

            Glide.with(characterDetailImage.context).load(
                validImageUrl
            ).placeholder(R.drawable.marvel_new_image).dontAnimate()
                .apply(
                    RequestOptions().override(
                        Constants.FIXED_IMAGE_WIDTH,
                        Constants.FIXED_IMAGE_HEIGHT
                    )
                )
                .into(binding.characterDetailImage)
        }
    }
}