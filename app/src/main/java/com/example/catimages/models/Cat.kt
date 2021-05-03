package com.example.catimages.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "cats")
data class Cat (
    val id : String,
    val url : String,
    val breeds : List<Breed>? = null,
    val categories : List<Categories>? = null,
    val width : Int,
    val height : Int
): Parcelable