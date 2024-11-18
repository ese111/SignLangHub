package com.example.signlanghub.data.repository

import com.example.signlanghub.data.model.SearchDTO
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface SearchRepository {
    suspend fun getSearch(keyword: String): Flow<Result<List<SearchDTO>>>

    suspend fun postImageSearch(file: MultipartBody.Part): Flow<Result<List<SearchDTO>>>
}
