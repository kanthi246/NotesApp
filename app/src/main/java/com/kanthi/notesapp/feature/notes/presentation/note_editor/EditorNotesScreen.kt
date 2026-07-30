package com.kanthi.notesapp.feature.notes.presentation.note_editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kanthi.notesapp.core.presentation.components.TextFieldComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorNotesScreen(
    onCancel: () -> Unit = {},
    onSaved: (noteId: Long) -> Unit = {},
    viewmodel: NoteEditorViewModel = hiltViewModel(),
) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.savedNoteId) {
        uiState.savedNoteId?.let { onSaved(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Cancel")
                    }
                },
                actions = {
                    IconButton(onClick = viewmodel::saveNote) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            TextFieldComponent(
                modifier = Modifier.fillMaxWidth(),
                labelValue = "Title",
                value = uiState.title,
                textStyle = MaterialTheme.typography.headlineMedium,
                onValueChange = viewmodel::onTitleChange
            )

            uiState.errorMessage?.let { message ->
                Text(text = message, style = MaterialTheme.typography.bodyMedium, color = Color.Red)
            }

            TextFieldComponent(
                modifier = Modifier.fillMaxWidth().weight(1f),
                labelValue = "Start writing…",
                value = uiState.description,
                textStyle = MaterialTheme.typography.bodyLarge,
                onValueChange = viewmodel::onDescriptionChange
            )
        }
    }
}
