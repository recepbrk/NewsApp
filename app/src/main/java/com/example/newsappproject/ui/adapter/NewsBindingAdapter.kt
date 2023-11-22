package com.example.newsappproject.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.newsappproject.R


class NewsBindingAdapter {
    companion object {
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
            imageView.load(imageUrl) {
                crossfade(800)
                crossfade(true)
                error(R.drawable.ic_placeholder)
            }
        }
    }
}