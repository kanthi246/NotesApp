package com.kanthi.notesapp.feature.notes.presentation.notes_list

import com.kanthi.notesapp.feature.notes.domain.model.NoteItem

sealed interface NotesListUiState {
    object Loading : NotesListUiState
    data class Success(val notes: List<NoteItem>) : NotesListUiState
    object Empty: NotesListUiState
}