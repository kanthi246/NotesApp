package com.kanthi.notesapp.domain.usecases

import com.kanthi.notesapp.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Long) = repository.deleteNote(noteId)
}
