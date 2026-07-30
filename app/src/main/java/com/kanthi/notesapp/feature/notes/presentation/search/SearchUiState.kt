package com.kanthi.notesapp.feature.notes.presentation.search

import com.kanthi.notesapp.feature.notes.presentation.model.NoteListItemUi

sealed interface SearchContent {
    data object EmptyQuery : SearchContent
    data object NoMatches : SearchContent
    data class Results(val notes: List<NoteListItemUi>) : SearchContent
}

data class SearchUiState(
    val query: String = "",
    val content: SearchContent = SearchContent.EmptyQuery
)
