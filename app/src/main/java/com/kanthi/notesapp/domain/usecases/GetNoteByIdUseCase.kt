package com.kanthi.notesapp.domain.usecases

import com.kanthi.notesapp.domain.model.NoteItem
import com.kanthi.notesapp.domain.repository.NotesRepository
import javax.inject.Inject

class GetNoteByIdUseCase@Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Long): NoteItem? = repository.getNoteById(id)
}

