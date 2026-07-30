package com.kanthi.notesapp.feature.notes.domain.usecase

import com.kanthi.notesapp.feature.notes.domain.model.NoteItem
import com.kanthi.notesapp.feature.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    operator fun invoke(): Flow<List<NoteItem>> = repository.getAllNotes()
    //operator fun invoke() — this lets us call the object like a function
    //getNotesUseCase() instead of getNotesUseCase.execute().
// Purely a Kotlin convenience, very common in Android codebases,
// and a thing interviewers like seeing you use correctly.
}