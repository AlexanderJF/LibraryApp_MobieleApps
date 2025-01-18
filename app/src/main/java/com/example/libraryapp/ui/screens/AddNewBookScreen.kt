package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.libraryapp.models.Book

@Composable
fun AddNewBookScreen(
    onSaveClick: (Book) -> Unit,  // Save the new book
    onCancelClick: () -> Unit    // Navigate back
) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") }
        )
        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN (optional)") }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { onSaveClick(Book(title = title, author = author, isbn = isbn)) }) {
                Text("Save")
            }
            Button(onClick = onCancelClick) {
                Text("Cancel")
            }
        }
    }
}