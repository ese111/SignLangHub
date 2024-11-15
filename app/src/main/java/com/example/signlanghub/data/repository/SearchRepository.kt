package com.example.signlanghub.data.repository

import com.example.signlanghub.data.model.SearchDTO
import okhttp3.MultipartBody

interface SearchRepository {
    suspend fun getSearch(keyword: String): Result<List<SearchDTO>>

    suspend fun postImageSearch(file: MultipartBody.Part): Result<List<SearchDTO>>
}
