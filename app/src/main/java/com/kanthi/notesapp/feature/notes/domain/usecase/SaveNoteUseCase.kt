package com.kanthi.notesapp.feature.notes.domain.usecase

import com.kanthi.notesapp.feature.notes.domain.model.NoteItem
import com.kanthi.notesapp.feature.notes.domain.repository.NotesRepository
import com.kanthi.notesapp.feature.notes.domain.model.SaveNoteError
import com.kanthi.notesapp.feature.notes.domain.model.SaveNoteResult
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: NoteItem): SaveNoteResult {
        if (note.title.isBlank() && note.description.isBlank()) {
            return SaveNoteResult.Error(SaveNoteError.EMPTY_NOTE)
        }
        val id = repository.saveNote(note)
        return SaveNoteResult.Success(id)
    }
}