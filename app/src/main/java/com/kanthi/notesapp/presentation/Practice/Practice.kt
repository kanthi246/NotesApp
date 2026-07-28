package com.kanthi.notesapp.presentation.Practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kanthi.notesapp.R

@Composable
@Preview
fun PracticeScreen(){

    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(20.dp),
        verticalArrangement = Arrangement.Top) {

        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center) {
            AsyncImage(
                model = "https://i.pravatar.cc/150?img=12",
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Text(text = "Kanthi Nalamati", modifier = Modifier.fillMaxWidth().padding(20.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp)

        Text(text = "Android Developer", modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        RowItem(R.drawable.outline_add_notes_24,"12 Notes")

        RowItem(R.drawable.outline_attach_file_24,"3 Folders")

        RowItem(R.drawable.outline_pinned_20,"5 Pinned")
    }
}

@Composable
fun RowItem(icon: Int,name : String){
    Spacer(modifier = Modifier.height(20.dp))

    Row(modifier = Modifier.fillMaxWidth()) {

        Image(painter = painterResource(id = icon), contentDescription = name)

        Spacer(modifier = Modifier.width(6.dp))

        Text(text = name,
            fontSize = 16.sp)

    }
}