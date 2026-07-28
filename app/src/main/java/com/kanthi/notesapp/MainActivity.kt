package com.kanthi.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kanthi.notesapp.domain.model.NoteItem
import com.kanthi.notesapp.presentation.noteslist.NoteCard
import com.kanthi.notesapp.presentation.noteslist.NoteListScreen
import com.kanthi.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Tells Hilt: "inject dependencies into this Activity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme(darkTheme = true) {
                NoteListScreen()
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun NoteCardPreviewLight() {
    NotesAppTheme(darkTheme = false) {
        NoteCard(note = NoteItem("Sample", "Some content here", "Today"))
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun NoteCardPreviewDark() {
    NotesAppTheme(darkTheme = true) {
        NoteCard(note = NoteItem("Sample", "Some content here", "Today"))
    }
}