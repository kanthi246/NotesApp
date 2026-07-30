package com.kanthi.notesapp.feature.notes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long,
    val pinned: Boolean = false
)