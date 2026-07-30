package com.kanthi.notesapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kanthi.notesapp.feature.notes.data.local.dao.NoteDao
import com.kanthi.notesapp.feature.notes.data.local.entity.NoteItemEntity

@Database(
    entities = [NoteItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}