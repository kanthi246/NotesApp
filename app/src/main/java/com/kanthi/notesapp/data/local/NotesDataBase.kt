package com.kanthi.notesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kanthi.notesapp.data.local.dao.NoteDao
import com.kanthi.notesapp.data.local.entity.NoteItemEntity

@Database(
    entities = [NoteItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}