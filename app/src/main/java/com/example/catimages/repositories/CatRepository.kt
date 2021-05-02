package com.example.catimages.repositories

import com.example.catimages.models.PagedCat

interface CatRepository {
    suspend fun getPagedCats(order: String, limit: Int, page: Int) : List<PagedCat>
}