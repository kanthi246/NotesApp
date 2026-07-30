package com.kanthi.notesapp.feature.notes.presentation.notes_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kanthi.notesapp.feature.notes.presentation.notes_list.components.NoteCard

@Preview
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),  // Hilt creates + injects the ViewModel
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = { AppTopBar() },
        floatingActionButton = { FAButton(onClick = {
            //click action
            //notes.add(NoteItem(System.currentTimeMillis(),"Sample", "Some content here"))
        }) }
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        when (uiState) {
            NotesListUiState.Empty -> {
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("No notes yet — tap + to add one")
                }
            }
            NotesListUiState.Loading -> {
                Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is NotesListUiState.Success -> {
                val notes = (uiState as NotesListUiState.Success).notes
                Column(modifier = Modifier.padding(paddingValues)) {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(10.dp)
                    ) {
                        items(items = notes) { note ->
                            NoteCard(note,
                                onClick = {}
                                , modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                "Notes App",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            //Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
        },
        actions = { AppBarActions() }
    )
}

@Composable
fun AppBarActions() {
    Row {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
//        Spacer(modifier = Modifier.width(10.dp))
//        Icon(imageVector = Icons.Default.Filter, contentDescription = "Filter")
    }
}


@Composable
fun FAButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = {
        //click action
        onClick()
    }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
    }
}
