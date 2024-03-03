package com.ulearning.ulearning_app.presentation.utils.imageLoader

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions

interface ImageLoader {
    fun loadImage(imageView: ImageView, imagePath: String, requestOptions: RequestOptions, placeHolder: Int)
    fun loadImage(imageView: ImageView, imagePath: Int, requestOptions: RequestOptions)
    fun loadImage(imageView: ImageView, imagePath: Int)
    fun loadImage(imageView: ImageView, imagePath: String)
}
