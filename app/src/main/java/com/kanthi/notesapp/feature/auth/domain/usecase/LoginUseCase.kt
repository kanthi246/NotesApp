package com.kanthi.notesapp.feature.auth.domain.usecase

import com.kanthi.notesapp.feature.auth.domain.model.AuthError
import com.kanthi.notesapp.feature.auth.domain.model.AuthResult
import com.kanthi.notesapp.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        if (email.isBlank() || password.isBlank()) return AuthResult.Error(AuthError.EMPTY_FIELDS)
        if (!AuthValidator.isValidEmail(email)) return AuthResult.Error(AuthError.INVALID_EMAIL)
        return repository.login(email.trim(), password)
    }
}
