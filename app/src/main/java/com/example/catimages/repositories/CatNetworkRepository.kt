package com.example.catimages.repositories

import com.example.catimages.models.Cat

interface CatNetworkRepository {
    suspend fun getNetworkCats(order: String, limit: Int, page: Int) : List<Cat>

}