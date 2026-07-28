package com.kanthi.notesapp.presentation.noteslist

import androidx.lifecycle.ViewModel
import com.kanthi.notesapp.domain.model.NoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel                 // Hilt manages this ViewModel's creation
class NoteListViewModel @Inject constructor(
    // Nothing injected yet — we add the repository in Phase 2
) : ViewModel() {

    val notes = mutableListOf<NoteItem>()


    // Temporary: just to verify Hilt creates the ViewModel correctly
    val message = "Hilt is working!"
    init {
        notes.add(NoteItem(title = "First Note","Hello Jetpack Compose","Today, 10:30 AM"))
        notes.add(NoteItem(title = "Second Note","Hello World ","Today, 10:35 AM"))
        notes.add(NoteItem(title = "Third Note","Tap me to expand this card and see more content that wraps and grows smoothly instead of jumping \n Hello Jetpack Compose","Today, 4:35 Pm"))
    }
}
