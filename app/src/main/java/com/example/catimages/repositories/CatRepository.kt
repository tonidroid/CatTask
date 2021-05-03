package com.example.catimages.repositories

import com.example.catimages.models.Cat

interface CatRepository {
    suspend fun getNetworkCats(order: String, limit: Int, page: Int) : List<Cat>

    suspend fun getLocalCats(): List<Cat>
}