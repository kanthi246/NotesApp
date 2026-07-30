package com.kanthi.notesapp.feature.notes.presentation.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.feature.auth.domain.usecase.LogoutUseCase
import com.kanthi.notesapp.feature.notes.domain.usecase.GetNotesUseCase
import com.kanthi.notesapp.feature.notes.presentation.model.toListItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val viewMode = MutableStateFlow(ViewMode.LIST)

    val uiState: StateFlow<NotesListUiState> = combine(getNotesUseCase(), viewMode) { notes, mode ->
        val content = if (notes.isEmpty()) {
            NotesListContent.Empty
        } else {
            val items = notes.sortedByDescending { it.updatedAt }.map { it.toListItemUi() }
            NotesListContent.Success(
                all = items,
                pinned = items.filter { it.pinned },
                others = items.filterNot { it.pinned }
            )
        }
        NotesListUiState(viewMode = mode, content = content)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NotesListUiState()
    )

    private val _loggedOut = MutableStateFlow(false)
    val loggedOut: StateFlow<Boolean> = _loggedOut.asStateFlow()

    fun onToggleViewMode() {
        viewMode.update { if (it == ViewMode.LIST) ViewMode.GRID else ViewMode.LIST }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            logoutUseCase()
            _loggedOut.value = true
        }
    }
}
