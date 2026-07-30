package com.kanthi.notesapp.feature.notes.data.repository

import com.kanthi.notesapp.feature.notes.data.local.dao.NoteDao
import com.kanthi.notesapp.feature.notes.data.mapper.toDomain
import com.kanthi.notesapp.feature.notes.data.mapper.toEntity
import com.kanthi.notesapp.feature.notes.domain.model.NoteItem
import com.kanthi.notesapp.feature.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {

    override fun getAllNotes(): Flow<List<NoteItem>> =
        noteDao.getAllNotes().map { list -> list.map { it.toDomain() } }

    override suspend fun getNoteById(id: Long): NoteItem? =
        noteDao.getNoteById(id)?.toDomain()

    override suspend fun saveNote(note: NoteItem): Long =
        noteDao.insertNote(note.toEntity())

    override suspend fun deleteNote(id: Long) {
        noteDao.deleteNote(id)
    }
}