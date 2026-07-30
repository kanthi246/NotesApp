package com.kanthi.notesapp.feature.auth.data.di

import com.kanthi.notesapp.feature.auth.data.repository.AuthRepositoryImpl
import com.kanthi.notesapp.feature.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
