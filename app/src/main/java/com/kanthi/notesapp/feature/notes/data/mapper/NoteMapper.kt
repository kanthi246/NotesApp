package com.kanthi.notesapp.feature.notes.data.mapper

import com.kanthi.notesapp.feature.notes.data.local.entity.NoteItemEntity
import com.kanthi.notesapp.feature.notes.domain.model.NoteItem

fun NoteItemEntity.toDomain(): NoteItem = NoteItem(
    id = id, title = title, description = description,
    createdAt = createdAt, updatedAt = updatedAt
)

fun NoteItem.toEntity(): NoteItemEntity = NoteItemEntity(
    id = id, title = title, description = description,
    createdAt = createdAt, updatedAt = updatedAt
)