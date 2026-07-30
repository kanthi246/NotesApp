package com.kanthi.notesapp.feature.auth.domain.model

sealed interface AuthResult {
    data class Success(val userId: Long) : AuthResult
    data class Error(val reason: AuthError) : AuthResult
}

enum class AuthError {
    EMPTY_FIELDS,
    EMPTY_NAME,
    INVALID_EMAIL,
    WEAK_PASSWORD,
    EMAIL_ALREADY_REGISTERED,
    INVALID_CREDENTIALS
}
