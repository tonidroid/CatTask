package com.example.catimages.ui.adapters

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catimages.models.PagedCat
import androidx.databinding.BindingAdapter
import com.example.catimages.R
import com.example.catimages.models.ResponseStatus

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PagedCat>?) {
    val adapter = recyclerView.adapter as ImageGridAdapter
    adapter.submitList(data)
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_loading_24)
                .error(R.drawable.ic_discon_24))
            .into(imgView)
    }
}


@BindingAdapter("responseStatus")
fun bindStatus(statusImageView: ImageView, status: ResponseStatus?) {
    when (status) {
        ResponseStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_loading_24)
        }
        ResponseStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_discon_24)
        }
        ResponseStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}