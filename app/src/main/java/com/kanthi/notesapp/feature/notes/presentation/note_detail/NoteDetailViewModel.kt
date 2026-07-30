package com.kanthi.notesapp.feature.notes.presentation.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.core.util.RelativeTimeFormatter
import com.kanthi.notesapp.feature.notes.domain.usecase.DeleteNoteUseCase
import com.kanthi.notesapp.feature.notes.domain.usecase.GetNotesUseCase
import com.kanthi.notesapp.feature.notes.domain.usecase.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    getNotesUseCase: GetNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId: Long = checkNotNull(savedStateHandle.get<Long>("noteId"))
    private val isDeleted = MutableStateFlow(false)

    val uiState: StateFlow<NoteDetailUiState> = combine(
        getNotesUseCase().map { notes -> notes.find { it.id == noteId } },
        isDeleted
    ) { note, deleted ->
        NoteDetailUiState(
            note = note,
            updatedLabel = note?.let { RelativeTimeFormatter.format(it.updatedAt) } ?: "",
            isLoading = false,
            isDeleted = deleted
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NoteDetailUiState()
    )

    fun onTogglePin() {
        val current = uiState.value.note ?: return
        viewModelScope.launch {
            saveNoteUseCase(current.copy(pinned = !current.pinned))
        }
    }

    fun onDelete() {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
            isDeleted.value = true
        }
    }
}
