package com.example.catimages.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.catimages.utils.BreedsConverters
import com.example.catimages.utils.CategoriesConverters
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "cats")
data class Cat (
    @PrimaryKey
    val id : String,
    val url : String,
    @TypeConverters(BreedsConverters::class)
    val breeds : List<Breed>,
    @TypeConverters(CategoriesConverters::class)
    val categories : List<Categories>,
    val width : Int,
    val height : Int
): Parcelable