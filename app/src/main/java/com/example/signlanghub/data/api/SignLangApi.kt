package com.example.signlanghub.data.api

import com.example.signlanghub.data.model.SearchDTO
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignLangApi {
    @GET("search-test")
    suspend fun getSearch(
        @Query("keyword") keyword: String,
    ): List<SearchDTO>

    @POST("search-test/upload")
    suspend fun postImageSearch(
        @Query("keyword") keyword: String,
    ): List<SearchDTO>
}
