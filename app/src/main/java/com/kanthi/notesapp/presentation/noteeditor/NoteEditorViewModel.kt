package com.kanthi.notesapp.presentation.noteeditor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.domain.model.NoteItem
import com.kanthi.notesapp.domain.usecases.GetNoteByIdUseCase
import com.kanthi.notesapp.domain.usecases.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId: Long? = savedStateHandle.get<Long>("noteId")?.takeIf { it != -1L }

    private val _uiState = MutableStateFlow(NoteEditorUiState(noteId = noteId))
    val uiState: StateFlow<NoteEditorUiState> = _uiState.asStateFlow()

    init {
        noteId?.let { loadNote(it) }
    }

    private fun loadNote(id: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val note = getNoteByIdUseCase(id)
            note?.let { found ->
                _uiState.update {
                    it.copy(title = found.title, description = found.description)
                }
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(title = newTitle) }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(description = newDescription) }
    }

    fun saveNote() {
        viewModelScope.launch {
            val current = _uiState.value
            val note = NoteItem(
                id = current.noteId ?: 0L,
                title = current.title,
                description = current.description
            )
            when (saveNoteUseCase(note)) {
                is SaveNoteResult.Success -> _uiState.update { it.copy(isSaved = true) }
                is SaveNoteResult.Error -> _uiState.update { it.copy(errorMessage = "Note can't be empty") }
            }
        }
    }
}