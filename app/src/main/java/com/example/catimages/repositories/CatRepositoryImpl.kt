package com.example.catimages.repositories

import com.example.catimages.models.PagedCat
import com.example.catimages.network.CatApi


class CatRepositoryImpl : CatRepository {

    override suspend fun getPagedCats(order: String, limit: Int, page: Int): List<PagedCat> {
        return CatApi.retrofitService.getPagedCats(
            "asc",limit, page)
    }
}