package com.example.signlanghub.di.repository

import com.example.signlanghub.data.repository.LoginRepository
import com.example.signlanghub.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
     @Binds
     abstract fun bindLoginRepository(
         loginRepositoryImpl: LoginRepositoryImpl
     ): LoginRepository
}