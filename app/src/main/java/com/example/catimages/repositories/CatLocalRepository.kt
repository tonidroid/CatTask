package com.example.catimages.repositories

import com.example.catimages.models.Cat

interface CatLocalRepository{
    suspend fun getLocalCats(): List<Cat>
}