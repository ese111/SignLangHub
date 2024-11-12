package com.example.signlanghub.data.repository

import com.example.signlanghub.data.model.SearchDTO

interface SearchRepository {
    suspend fun getSearch(keyword: String): Result<List<SearchDTO>>

    suspend fun postImageSearch(keyword: String): Result<List<SearchDTO>>
}
