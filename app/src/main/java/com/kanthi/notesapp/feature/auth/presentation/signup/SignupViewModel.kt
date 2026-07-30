package com.kanthi.notesapp.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.feature.auth.domain.model.AuthError
import com.kanthi.notesapp.feature.auth.domain.model.AuthResult
import com.kanthi.notesapp.feature.auth.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState: StateFlow<SignupUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, errorMessage = null) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun submit() {
        val current = _uiState.value
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result = registerUseCase(current.name, current.email, current.password)) {
                is AuthResult.Success -> _uiState.update { it.copy(isLoading = false, signupSucceeded = true) }
                is AuthResult.Error -> _uiState.update {
                    it.copy(isLoading = false, errorMessage = messageFor(result.reason))
                }
            }
        }
    }

    private fun messageFor(reason: AuthError): String = when (reason) {
        AuthError.EMPTY_NAME -> "Name can't be empty"
        AuthError.INVALID_EMAIL -> "Enter a valid email address"
        AuthError.WEAK_PASSWORD -> "Password must be at least 8 characters"
        AuthError.EMAIL_ALREADY_REGISTERED -> "An account with this email already exists"
        else -> "Something went wrong. Please try again."
    }
}
