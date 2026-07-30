package com.kanthi.notesapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kanthi.notesapp.feature.auth.data.local.dao.UserDao
import com.kanthi.notesapp.feature.auth.data.local.entity.UserEntity
import com.kanthi.notesapp.feature.notes.data.local.dao.NoteDao
import com.kanthi.notesapp.feature.notes.data.local.entity.NoteItemEntity

@Database(
    entities = [NoteItemEntity::class, UserEntity::class],
    version = 3,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun userDao(): UserDao
}
