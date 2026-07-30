package com.kanthi.notesapp.feature.auth.domain.repository

import com.kanthi.notesapp.feature.auth.domain.model.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): AuthResult
    suspend fun login(email: String, password: String): AuthResult
    suspend fun logout()
    fun currentUserId(): Flow<Long?>
}
