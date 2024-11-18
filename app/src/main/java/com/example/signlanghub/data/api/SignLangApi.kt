package com.example.signlanghub.data.api

import com.example.signlanghub.data.model.SearchDTO
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SignLangApi {
    @GET("search")
    suspend fun getSearch(
        @Query("keyword") keyword: String,
    ): List<SearchDTO>

    @Multipart
    @POST("search/upload")
    suspend fun postImageSearch(
        @Part file: MultipartBody.Part,
    ): List<SearchDTO>
}
