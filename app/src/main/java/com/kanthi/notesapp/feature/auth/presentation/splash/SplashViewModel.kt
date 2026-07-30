package com.kanthi.notesapp.feature.auth.presentation.splash

import androidx.lifecycle.ViewModel
import com.kanthi.notesapp.feature.auth.domain.usecase.GetSessionUseCase
import kotlinx.coroutines.flow.first
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase
) : ViewModel() {
    suspend fun isLoggedIn(): Boolean = getSessionUseCase().first() != null
}
