package com.kanthi.notesapp.feature.notes.domain.repository

import com.kanthi.notesapp.feature.notes.domain.model.NoteItem
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getAllNotes(): Flow<List<NoteItem>>
    suspend fun getNoteById(id: Long): NoteItem?
    suspend fun saveNote(note: NoteItem): Long
    suspend fun deleteNote(id: Long)
}