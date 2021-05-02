package com.example.catimages.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categories (

    val id : Int? = null,
    val name : String? = null
): Parcelable