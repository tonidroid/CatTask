package com.example.catimages.models

data class PagedCats (

    val breeds : List<String>,
    val categories : List<Categories>,
    val id : Int,
    val url : String,
    val width : Int,
    val height : Int
)