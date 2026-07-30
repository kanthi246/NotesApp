package com.kanthi.notesapp.feature.auth.domain.usecase

import android.util.Patterns

internal object AuthValidator {
    const val MIN_PASSWORD_LENGTH = 8

    fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
