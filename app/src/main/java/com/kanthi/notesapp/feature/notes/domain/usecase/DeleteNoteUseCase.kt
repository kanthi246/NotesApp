package com.kanthi.notesapp.feature.notes.domain.usecase

import com.kanthi.notesapp.feature.notes.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Long) = repository.deleteNote(noteId)
}
