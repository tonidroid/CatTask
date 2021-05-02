package com.example.catimages.network


import com.example.catimages.models.PagedCat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com/v1/"

private val loggingInterceptor = HttpLoggingInterceptor()
private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface CatApiService {
    @Headers("x-api-key: c5d82522-e83b-42aa-b300-0efe51a90f1f")
    @GET("images/search")
    suspend fun getPagedCats(
        @Query("order") order: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<PagedCat>
}

object CatApi {
    val retrofitService : CatApiService by lazy { retrofit.create(CatApiService::class.java) }
}