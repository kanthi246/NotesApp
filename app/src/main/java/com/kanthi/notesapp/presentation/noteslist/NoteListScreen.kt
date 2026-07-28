package com.kanthi.notesapp.presentation.noteslist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.kanthi.notesapp.domain.model.NoteItem

@Preview
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),  // Hilt creates + injects the ViewModel
) {
    NotesScreen(viewModel.notes)
}


@Composable
fun NotesScreen(notes: MutableList<NoteItem>) {
    var isVisible by remember { mutableStateOf(false) }

    // Runs once when NotesScreen first appears
    LaunchedEffect(Unit) {
        isVisible = true
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = { AppTopBar() },
        floatingActionButton = { FAButton() }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = notes){ note ->
                    AnimatedVisibility(visible = isVisible) {
                        NoteCard(note)
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
        title = { Text("Notes App", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
        navigationIcon = { Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back") },
        actions = { AppBarActions() }
    )
}

@Composable
fun AppBarActions(){
    Row {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        Spacer(modifier = Modifier.width(10.dp))
        Icon(imageVector = Icons.Default.Filter, contentDescription = "Filter")
    }
}


@Composable
fun FAButton() {
    FloatingActionButton(onClick = {
        //click action
    }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
    }
}


@Composable
fun NoteCard(note: NoteItem) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth().animateContentSize(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            isExpanded = !isExpanded
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = note.title, style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth(),
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.content, style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth(),
                maxLines = if(isExpanded) Int.MAX_VALUE else 2, overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.date, style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth(),
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }
    }
}