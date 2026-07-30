package com.kanthi.notesapp.feature.notes.presentation.notes_list

import com.kanthi.notesapp.feature.notes.presentation.model.NoteListItemUi

enum class ViewMode { LIST, GRID }

sealed interface NotesListContent {
    data object Loading : NotesListContent
    data object Empty : NotesListContent
    data class Success(
        val all: List<NoteListItemUi>,
        val pinned: List<NoteListItemUi>,
        val others: List<NoteListItemUi>
    ) : NotesListContent
}

data class NotesListUiState(
    val viewMode: ViewMode = ViewMode.LIST,
    val content: NotesListContent = NotesListContent.Loading
)
