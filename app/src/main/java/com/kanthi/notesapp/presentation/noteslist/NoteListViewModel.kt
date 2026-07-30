package com.kanthi.notesapp.presentation.noteslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanthi.notesapp.domain.model.NoteItem
import com.kanthi.notesapp.domain.repository.NotesRepository
import com.kanthi.notesapp.domain.usecases.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel // Hilt manages this ViewModel's creation
class NoteListViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase) : ViewModel() {

    val uiState: StateFlow<NotesListUiState> = getNotesUseCase().map { notes ->
            if (notes.isEmpty()) NotesListUiState.Empty
            else NotesListUiState.Success(notes)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NotesListUiState.Loading
        )
}

//getNotesUseCase() returns a cold Flow — it does nothing until someone starts collecting it,
// and if two collectors subscribe, Room would technically run the query twice.

//.stateIn(...) converts that cold Flow into a hot StateFlow — it starts collecting once (per the started policy)
// and shares that one stream with every screen/composable observing it.
// Also gives you .value, always has a current value.

//SharingStarted.WhileSubscribed(5_000) — start collecting from Room when the first UI subscriber appears,
// and keep collecting until 5 seconds after the last subscriber leaves.
// That 5-second grace period is specifically to survive configuration changes (screen rotation) without restarting the Room query from scratch.

//initialValue = Loading — a StateFlow must always have some value even before the first real emission arrives,
// so the UI has something to render on the very first frame.
//
//@HiltViewModel + @Inject constructor — Hilt has a special integration for ViewModels specifically
// (this annotation, plus hiltViewModel() on the Compose side below)
// since ViewModels have their own lifecycle Hilt needs to respect.
