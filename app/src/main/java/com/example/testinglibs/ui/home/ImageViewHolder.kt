package com.example.testinglibs.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testinglibs.R
import com.example.testinglibs.databinding.SingleItemBinding
import com.example.testinglibs.models.FlickrPhoto

class ImageViewHolder(private val binding: SingleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: FlickrPhoto.PhotoItem) {
        binding.apply {
            Glide.with(itemView)
                .load(photo.attributionUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .into(imageView)
        }
    }
}

