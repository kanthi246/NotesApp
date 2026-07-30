package com.kanthi.notesapp.presentation.noteeditor

data class NoteEditorUiState(
    val noteId: Long? = null,
    var title: String = "",
    var description: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null
)