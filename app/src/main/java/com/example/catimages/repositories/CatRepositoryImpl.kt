package com.example.catimages.repositories

import com.example.catimages.models.Cat
import com.example.catimages.network.CatApi


class CatRepositoryImpl  {

    //I use stock order 'asc'
    suspend fun getNetworkCats(order: String, limit: Int, page: Int) =
        CatApi.retrofitService.getPagedCats("asc",limit, page)

//    suspend fun getLocalCats(): List<Cat> {}
}