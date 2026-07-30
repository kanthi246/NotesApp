package com.kanthi.notesapp.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.feature.auth.domain.model.AuthError
import com.kanthi.notesapp.feature.auth.domain.model.AuthResult
import com.kanthi.notesapp.feature.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

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
            when (val result = loginUseCase(current.email, current.password)) {
                is AuthResult.Success -> _uiState.update { it.copy(isLoading = false, loginSucceeded = true) }
                is AuthResult.Error -> _uiState.update {
                    it.copy(isLoading = false, errorMessage = messageFor(result.reason))
                }
            }
        }
    }

    private fun messageFor(reason: AuthError): String = when (reason) {
        AuthError.EMPTY_FIELDS -> "Email and password are required"
        AuthError.INVALID_EMAIL -> "Enter a valid email address"
        AuthError.INVALID_CREDENTIALS -> "Incorrect email or password"
        else -> "Something went wrong. Please try again."
    }
}
