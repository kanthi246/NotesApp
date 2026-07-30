package com.kanthi.notesapp.feature.notes.domain.model

data class NoteItem(
    val id: Long = 0L,
    val title: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val pinned: Boolean = false)
