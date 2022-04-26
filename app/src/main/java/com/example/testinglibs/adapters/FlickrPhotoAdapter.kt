package com.example.testinglibs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.testinglibs.databinding.SingleItemBinding
import com.example.testinglibs.models.FlickrPhoto
import com.example.testinglibs.ui.home.ImageViewHolder


class FlickrPhotoAdapter :
    PagingDataAdapter<FlickrPhoto.PhotoItem, ImageViewHolder>(
        PHOTO_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<FlickrPhoto.PhotoItem>() {
            override fun areItemsTheSame(
                oldItem: FlickrPhoto.PhotoItem,
                newItem: FlickrPhoto.PhotoItem
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: FlickrPhoto.PhotoItem,
                newItem: FlickrPhoto.PhotoItem
            ) =
                oldItem == newItem
        }
    }
}