package com.kanthi.notesapp.feature.auth.domain.usecase

import com.kanthi.notesapp.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.logout()
}
