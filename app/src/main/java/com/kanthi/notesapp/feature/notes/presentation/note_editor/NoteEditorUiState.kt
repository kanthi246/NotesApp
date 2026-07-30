package com.kanthi.notesapp.feature.notes.presentation.note_editor

data class NoteEditorUiState(
    val noteId: Long? = null,
    var title: String = "",
    var description: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null
)