package com.kanthi.notesapp.feature.auth.presentation.signup

data class SignupUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val signupSucceeded: Boolean = false
)
