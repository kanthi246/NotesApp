package com.kanthi.notesapp.domain.usecases

import com.kanthi.notesapp.domain.model.NoteItem
import com.kanthi.notesapp.domain.repository.NotesRepository
import com.kanthi.notesapp.presentation.noteeditor.SaveNoteError
import com.kanthi.notesapp.presentation.noteeditor.SaveNoteResult
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