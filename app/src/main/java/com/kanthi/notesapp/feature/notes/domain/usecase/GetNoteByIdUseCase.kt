package com.kanthi.notesapp.feature.notes.domain.usecase

import com.kanthi.notesapp.feature.notes.domain.model.NoteItem
import com.kanthi.notesapp.feature.notes.domain.repository.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase@Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Long): NoteItem? = repository.getNoteById(id)
}

