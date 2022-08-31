package com.talentomobile.marvel.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.talentomobile.marvel.R
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Constants
import com.talentomobile.marvel.databinding.RecyclerViewRowsBinding

class HomeRecyclerAdapter : RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<MarvelCharacter>() {
        override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MarvelCharacter,
            newItem: MarvelCharacter
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerViewRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.bind(character)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


   inner class MyViewHolder(private val binding: RecyclerViewRowsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(marvelCharacter: MarvelCharacter) {

            val validImageUrl = marvelCharacter.thumbnail.path.replace(
                Constants.INVALID_URL_FORMAT,
                Constants.VALID_URL_FORMAT
            ).plus(".").plus(marvelCharacter.thumbnail.extension)

            binding.apply {
                nameTextField.text = marvelCharacter.name
                idTextField.text = marvelCharacter.id.toString()
                comicTextField.text = marvelCharacter.comics.available.toString()

                Glide.with(characterImage.context).load(
                    validImageUrl
                ).placeholder(R.drawable.marvel_new_image).dontAnimate()
                    .apply(
                        RequestOptions().override(
                            Constants.FIXED_IMAGE_WIDTH,
                            Constants.FIXED_IMAGE_HEIGHT
                        )
                    )
                    .into(binding.characterImage)
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(marvelCharacter)
                }
            }
        }
    }

    private var onItemClickListener: ((MarvelCharacter) -> Unit)? = null

    fun setOnItemClickListener(listener: (MarvelCharacter) -> Unit) {
        onItemClickListener = listener
    }
}