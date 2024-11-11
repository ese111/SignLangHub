package com.example.signlanghub.data.repository

interface LoginRepository {
    fun login(email: String, password: String): Result<Unit>
}