package com.kanthi.notesapp.feature.auth.domain.usecase

import com.kanthi.notesapp.feature.auth.domain.model.AuthError
import com.kanthi.notesapp.feature.auth.domain.model.AuthResult
import com.kanthi.notesapp.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): AuthResult {
        if (name.isBlank()) return AuthResult.Error(AuthError.EMPTY_NAME)
        if (!AuthValidator.isValidEmail(email)) return AuthResult.Error(AuthError.INVALID_EMAIL)
        if (password.length < AuthValidator.MIN_PASSWORD_LENGTH) {
            return AuthResult.Error(AuthError.WEAK_PASSWORD)
        }
        return repository.register(name.trim(), email.trim(), password)
    }
}
