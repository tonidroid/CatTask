package com.example.catimages.ui.adapters

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.databinding.BindingAdapter
import com.example.catimages.R
import com.example.catimages.models.Cat
import com.example.catimages.models.ResponseStatus


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Cat>?) {
    val adapter = recyclerView.adapter as ImageGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_discon_24))
            .into(imgView)
    }
}


@BindingAdapter("responseStatus")
fun bindStatus(statusImageView: ImageView, networkState: ResponseStatus?) {
    when (networkState) {
        ResponseStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
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