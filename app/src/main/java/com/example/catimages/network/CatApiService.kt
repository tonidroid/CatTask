package com.example.catimages.network


import com.example.catimages.models.Cat
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query




interface CatApiService {
    @Headers("x-api-key: c5d82522-e83b-42aa-b300-0efe51a90f1f")
    @GET("images/search")
    suspend fun getPagedCats(
        @Query("order") order: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<Cat>
}

