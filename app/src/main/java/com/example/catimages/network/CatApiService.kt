package com.example.catimages.network


import com.example.catimages.models.PagedCats
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com/v1"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatApiService {
    @Headers("x-api-key: c5d82522-e83b-42aa-b300-0efe51a90f1f")
    @GET("/images/search")
    suspend fun getProperties(@Query("filter") type: String): List<PagedCats>
}

object CatApi {
    val retrofitService : CatApiService by lazy { retrofit.create(CatApiService::class.java) }
}