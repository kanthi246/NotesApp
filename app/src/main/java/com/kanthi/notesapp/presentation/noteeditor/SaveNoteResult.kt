package com.kanthi.notesapp.presentation.noteeditor

sealed interface SaveNoteResult {
    data class Success(val noteId: Long) : SaveNoteResult
    data class Error(val reason: SaveNoteError) : SaveNoteResult
}

enum class SaveNoteError {
    EMPTY_NOTE
}