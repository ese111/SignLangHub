package com.example.signlanghub.data.repository

import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(

) : LoginRepository {
    override fun login(email: String, password: String): Result<Unit> {
        return if (email == "test" && password == "1234") {
            Result.success(Unit)
        } else {
            Result.failure(Exception("로그인 실패"))
        }
    }
}