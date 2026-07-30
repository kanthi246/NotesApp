package com.kanthi.notesapp.feature.notes.presentation.note_editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kanthi.notesapp.core.presentation.components.TextFieldComponent

@Composable
fun EditorNotesScreen(
    onNavigateBack: () -> Unit = {},
    viewmodel: NoteEditorViewModel = hiltViewModel(),
) {
    val uiState by viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isSaved) {
        if (uiState.isSaved) onNavigateBack()
    }
    AddNotesContent(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onTitleChange = viewmodel::onTitleChange,
        onDescriptionChange = viewmodel::onDescriptionChange,
        onSaveClick = viewmodel::saveNote
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotesContent(
    uiState: NoteEditorUiState,
    onNavigateBack: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(if (uiState.noteId != null) "Edit Note" else "Add Notes") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack){
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                    )
                }
            },
            actions = { IconButton(onClick = onSaveClick){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save",
                )
            } }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            val fieldModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

            TextFieldComponent(
                modifier = fieldModifier,
                labelValue = "Title",
                value = uiState.title,
                onValueChange = { onTitleChange(it) }
            )

            TextFieldComponent(
                modifier = fieldModifier.weight(1f),
                labelValue = "Description",
                value = uiState.description,
                onValueChange = { onDescriptionChange(it) }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun AddNotesContentPreview() {
    AddNotesContent(
        uiState = NoteEditorUiState(title = "Sample title",
            description = "Sample description"),
        onNavigateBack = {},
        onTitleChange = {},
        onDescriptionChange = {},
        onSaveClick = {}
    )
}


