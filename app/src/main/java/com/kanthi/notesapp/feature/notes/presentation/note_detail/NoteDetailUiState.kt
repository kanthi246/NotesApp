package com.kanthi.notesapp.feature.notes.presentation.note_detail

import com.kanthi.notesapp.feature.notes.domain.model.NoteItem

data class NoteDetailUiState(
    val note: NoteItem? = null,
    val updatedLabel: String = "",
    val isLoading: Boolean = true,
    val isDeleted: Boolean = false
)
