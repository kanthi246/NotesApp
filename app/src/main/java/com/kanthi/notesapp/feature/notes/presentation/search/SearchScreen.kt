package com.kanthi.notesapp.feature.notes.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kanthi.notesapp.core.presentation.components.TextFieldComponent
import com.kanthi.notesapp.feature.notes.presentation.notes_list.components.NoteRow
import com.kanthi.notesapp.feature.notes.presentation.notes_list.components.NoteRowDivider

@Composable
fun SearchScreen(
    onBack: () -> Unit = {},
    onNoteClick: (Long) -> Unit = {},
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                    Text(text = "Search", style = MaterialTheme.typography.headlineSmall)
                }
                TextFieldComponent(
                    modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                    value = uiState.query,
                    labelValue = "Search your notes",
                    onValueChange = viewModel::onQueryChange
                )
            }
        }
    ) { paddingValues ->
        when (val content = uiState.content) {
            SearchContent.EmptyQuery -> {
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("Start typing to search your notes.", style = MaterialTheme.typography.bodyMedium)
                }
            }
            SearchContent.NoMatches -> {
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("No matches for \"${uiState.query}\".", style = MaterialTheme.typography.bodyMedium)
                }
            }
            is SearchContent.Results -> {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                    items(content.notes, key = { it.id }) { note ->
                        NoteRow(note = note, onClick = { onNoteClick(note.id) })
                        NoteRowDivider()
                    }
                }
            }
        }
    }
}
