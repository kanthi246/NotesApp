package com.kanthi.notesapp.feature.auth.data.repository

import com.kanthi.notesapp.feature.auth.data.local.dao.UserDao
import com.kanthi.notesapp.feature.auth.data.local.datastore.SessionDataStore
import com.kanthi.notesapp.feature.auth.data.local.entity.UserEntity
import com.kanthi.notesapp.feature.auth.data.security.PasswordHasher
import com.kanthi.notesapp.feature.auth.domain.model.AuthError
import com.kanthi.notesapp.feature.auth.domain.model.AuthResult
import com.kanthi.notesapp.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionDataStore: SessionDataStore
) : AuthRepository {

    override suspend fun register(name: String, email: String, password: String): AuthResult {
        if (userDao.getUserByEmail(email) != null) {
            return AuthResult.Error(AuthError.EMAIL_ALREADY_REGISTERED)
        }
        val salt = PasswordHasher.generateSalt()
        val hash = PasswordHasher.hash(password, salt)
        val userId = userDao.insertUser(
            UserEntity(name = name, email = email, passwordHash = hash, passwordSalt = salt)
        )
        sessionDataStore.setCurrentUserId(userId)
        return AuthResult.Success(userId)
    }

    override suspend fun login(email: String, password: String): AuthResult {
        val user = userDao.getUserByEmail(email)
            ?: return AuthResult.Error(AuthError.INVALID_CREDENTIALS)
        if (!PasswordHasher.verify(password, user.passwordSalt, user.passwordHash)) {
            return AuthResult.Error(AuthError.INVALID_CREDENTIALS)
        }
        sessionDataStore.setCurrentUserId(user.id)
        return AuthResult.Success(user.id)
    }

    override suspend fun logout() {
        sessionDataStore.clearSession()
    }

    override fun currentUserId(): Flow<Long?> = sessionDataStore.currentUserId
}
