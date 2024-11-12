package com.example.signlanghub.data.repository

import com.example.signlanghub.data.api.SignLangApi
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchRepositoryImpl
    @Inject
    constructor(
        private val signLangApi: SignLangApi,
    ) : BaseRepository(),
        SearchRepository {
        override suspend fun getSearch(keyword: String) =
            makeApiCall(dispatcher = Dispatchers.IO) {
                signLangApi.getSearch(keyword)
            }

        override suspend fun postImageSearch(keyword: String) =
            makeApiCall(dispatcher = Dispatchers.IO) {
                signLangApi.postImageSearch(keyword)
            }
    }
