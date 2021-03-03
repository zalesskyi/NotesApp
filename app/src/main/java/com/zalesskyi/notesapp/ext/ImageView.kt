package com.zalesskyi.notesapp.ext

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(bmp: Bitmap) {
    Glide.with(context)
        .load(bmp)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .apply(RequestOptions().centerCrop())
        .into(this)
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
        .apply(RequestOptions().centerCrop())
        .into(this)
}