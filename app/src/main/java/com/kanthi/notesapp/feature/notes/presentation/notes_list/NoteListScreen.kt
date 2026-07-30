package com.kanthi.notesapp.feature.notes.presentation.notes_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kanthi.notesapp.feature.notes.presentation.notes_list.components.NoteGridCard
import com.kanthi.notesapp.feature.notes.presentation.notes_list.components.NoteRow
import com.kanthi.notesapp.feature.notes.presentation.notes_list.components.NoteRowDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    onNoteClick: (Long) -> Unit = {},
    onAddNote: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onLoggedOut: () -> Unit = {},
    viewModel: NoteListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val loggedOut by viewModel.loggedOut.collectAsStateWithLifecycle()

    LaunchedEffect(loggedOut) {
        if (loggedOut) onLoggedOut()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Notes", style = MaterialTheme.typography.headlineSmall) },
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = viewModel::onLogoutClick) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Log out")
                    }
                    IconButton(onClick = viewModel::onToggleViewMode) {
                        Icon(
                            imageVector = if (uiState.viewMode == ViewMode.LIST) Icons.Default.GridView else Icons.AutoMirrored.Filled.ViewList,
                            contentDescription = "Toggle view"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Add note")
            }
        }
    ) { paddingValues ->
        when (val content = uiState.content) {
            NotesListContent.Loading -> {
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            NotesListContent.Empty -> {
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("No notes yet.", style = MaterialTheme.typography.bodyMedium)
                }
            }
            is NotesListContent.Success -> {
                if (uiState.viewMode == ViewMode.LIST) {
                    NotesListView(content, paddingValues, onNoteClick)
                } else {
                    NotesGridView(content, paddingValues, onNoteClick)
                }
            }
        }
    }
}

@Composable
private fun NotesListView(
    content: NotesListContent.Success,
    paddingValues: PaddingValues,
    onNoteClick: (Long) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        if (content.pinned.isNotEmpty()) {
            item { SectionHeader("Pinned") }
            items(content.pinned, key = { "pinned-${it.id}" }) { note ->
                NoteRow(note = note, onClick = { onNoteClick(note.id) })
                NoteRowDivider()
            }
        }
        item { SectionHeader("All notes") }
        items(content.others, key = { "other-${it.id}" }) { note ->
            NoteRow(note = note, onClick = { onNoteClick(note.id) })
            NoteRowDivider()
        }
    }
}

@Composable
private fun NotesGridView(
    content: NotesListContent.Success,
    paddingValues: PaddingValues,
    onNoteClick: (Long) -> Unit
) {
    val allNotes = content.all
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        contentPadding = PaddingValues(12.dp),
        verticalItemSpacing = 12.dp
    ) {
        items(allNotes, span = { StaggeredGridItemSpan.SingleLane }, key = { it.id }) { note ->
            NoteGridCard(note = note, onClick = { onNoteClick(note.id) }, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text.uppercase(),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
    )
}
