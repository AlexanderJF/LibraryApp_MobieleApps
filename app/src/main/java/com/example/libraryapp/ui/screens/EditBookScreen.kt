package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.libraryapp.models.Book

@Composable
fun EditBookScreen(
    book: LiveData<Book?>,
    onSaveClick: (Book) -> Unit,
    onDeleteClick: (Book) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Observe the LiveData
    val bookData by book.observeAsState()

    // Initialize state variables based on the observed book data
    var title by remember { mutableStateOf(bookData?.title ?: "") }
    var author by remember { mutableStateOf(bookData?.author ?: "") }
    var isbn by remember { mutableStateOf(bookData?.isbn ?: "") }

    // Update state variables when bookData changes
    LaunchedEffect(bookData) {
        title = bookData?.title ?: ""
        author = bookData?.author ?: ""
        isbn = bookData?.isbn ?: ""
    }

    Column(
        modifier = modifier.padding(16.dp),
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
            label = { Text("ISBN (Optional)") }
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                // Check if bookData is not null before copying
                bookData?.let { existingBook ->
                    onSaveClick(existingBook.copy(title = title, author = author, isbn = isbn.ifBlank { null }))
                }
            }) {
                Text("Save")
            }
            Button(
                onClick = {
                    // Check if bookData is not null before deleting
                    bookData?.let { existingBook ->
                        onDeleteClick(existingBook)
                    }
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Text("Delete")
            }
            OutlinedButton(onClick = onCancelClick) {
                Text("Cancel")
            }
        }
    }
}