package com.kanthi.notesapp.feature.notes.presentation.model

import com.kanthi.notesapp.core.util.RelativeTimeFormatter
import com.kanthi.notesapp.feature.notes.domain.model.NoteItem

data class NoteListItemUi(
    val id: Long,
    val title: String,
    val preview: String,
    val updatedLabel: String,
    val pinned: Boolean
)

fun NoteItem.toListItemUi(): NoteListItemUi = NoteListItemUi(
    id = id,
    title = title.ifBlank { "Untitled" },
    preview = description,
    updatedLabel = RelativeTimeFormatter.format(updatedAt),
    pinned = pinned
)
