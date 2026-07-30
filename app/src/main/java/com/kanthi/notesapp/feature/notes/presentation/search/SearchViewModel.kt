package com.kanthi.notesapp.feature.notes.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.feature.notes.domain.usecase.GetNotesUseCase
import com.kanthi.notesapp.feature.notes.presentation.model.toListItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")

    val uiState: StateFlow<SearchUiState> = query.flatMapLatest { q ->
        if (q.isBlank()) {
            flowOf(SearchUiState(query = q, content = SearchContent.EmptyQuery))
        } else {
            getNotesUseCase().map { notes ->
                val matches = notes.filter { note ->
                    note.title.contains(q, ignoreCase = true) || note.description.contains(q, ignoreCase = true)
                }
                val content = if (matches.isEmpty()) {
                    SearchContent.NoMatches
                } else {
                    SearchContent.Results(matches.sortedByDescending { it.updatedAt }.map { it.toListItemUi() })
                }
                SearchUiState(query = q, content = content)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState()
    )

    fun onQueryChange(newQuery: String) {
        query.update { newQuery }
    }
}
