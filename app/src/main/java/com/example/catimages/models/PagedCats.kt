package com.example.catimages.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PagedCat (
    val id : String,
    val url : String,
    val breeds : List<Breed>? = null,
    val categories : List<Categories>? = null,
    val width : Int,
    val height : Int
): Parcelable