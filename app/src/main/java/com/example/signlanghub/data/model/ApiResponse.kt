package com.example.signlanghub.data.model

sealed class ApiResponse {
    data class Success<T>(
        val data: T,
    ) : ApiResponse()

    data class Error(
        val message: String,
    ) : ApiResponse()

    data object NoContent : ApiResponse()
}
