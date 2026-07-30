package com.kanthi.notesapp.feature.auth.domain.usecase

import com.kanthi.notesapp.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<Long?> = repository.currentUserId()
}
