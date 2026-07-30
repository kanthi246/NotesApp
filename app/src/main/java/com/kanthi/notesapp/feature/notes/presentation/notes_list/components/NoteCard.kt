package com.kanthi.notesapp.feature.notes.presentation.notes_list.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kanthi.notesapp.feature.notes.domain.model.NoteItem

@Composable
fun NoteCard(note: NoteItem,
             onClick: () -> Unit,
             modifier: Modifier = Modifier) {
    //var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth().animateContentSize(),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp),
        border = CardDefaults.outlinedCardBorder(),
        onClick = {
            onClick()
            //isExpanded = !isExpanded
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = note.title, style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth(),
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.description, style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth(),
                maxLines = 2, overflow = TextOverflow.Ellipsis
                //maxLines = if(isExpanded) Int.MAX_VALUE else 2, overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.createdAt.toString(), style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier
                    .padding(vertical = 6.dp)
                    .fillMaxWidth(),
                maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }
    }
}
