package com.example.signlanghub.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseRepository {
    suspend fun <T> makeApiCall(
        dispatcher: CoroutineDispatcher,
        call: suspend () -> T,
    ): Result<T> =
        runCatching {
            withContext(dispatcher) {
                call.invoke()
            }
        }
}
