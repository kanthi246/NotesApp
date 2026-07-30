package com.kanthi.notesapp.presentation.noteslist

import com.kanthi.notesapp.domain.model.NoteItem

sealed interface NotesListUiState {
    object Loading : NotesListUiState
    data class Success(val notes: List<NoteItem>) : NotesListUiState
    object Empty: NotesListUiState
}