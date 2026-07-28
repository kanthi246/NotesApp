package com.kanthi.notesapp.presentation.noteslist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddNotesScreen() {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Add Notes") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back"
                )
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.White)
        ) {

            val fieldModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Enter Title") },
                modifier = fieldModifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Add Your Description") },
                modifier = fieldModifier
                    .height(200.dp),
                maxLines = 10
            )

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedVisibility(visible = title.isNotBlank()) {
                Text("Error")
            }

            Button(
                onClick = { },
                enabled = title.isNotBlank(),
                modifier = fieldModifier
            ) {
                Text("Save Note")
            }
        }
    }
}


