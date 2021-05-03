package com.example.catimages.repositories

import com.example.catimages.models.Cat
import com.example.catimages.network.CatApi


class CatRepositoryImpl : CatRepository {

    //I use stock order 'asc'
    override suspend fun getNetworkCats(order: String, limit: Int, page: Int) =
        CatApi.retrofitService.getPagedCats("asc",limit, page)

    override suspend fun getLocalCats(): List<Cat> {
        TODO("Not yet implemented")
    }

}