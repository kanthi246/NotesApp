package com.kanthi.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kanthi.notesapp.domain.model.NoteItem
import com.kanthi.notesapp.presentation.noteslist.NoteListScreen
import com.kanthi.notesapp.presentation.common.components.NoteCard
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


//for the preview of the NoteCard
@Preview(showBackground = true, name = "Light Mode")
@Composable
fun NoteCardPreviewLight() {
    NotesAppTheme(darkTheme = false) {
        NoteCard(note = NoteItem(System.currentTimeMillis(),"Sample", "Some content here"),
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//for the preview of the NoteCard
@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun NoteCardPreviewDark() {
    NotesAppTheme(darkTheme = true) {
        NoteCard(note = NoteItem(System.currentTimeMillis(),"Sample", "Some content here"),
            onClick = {},
            modifier = Modifier.fillMaxWidth())
    }
}