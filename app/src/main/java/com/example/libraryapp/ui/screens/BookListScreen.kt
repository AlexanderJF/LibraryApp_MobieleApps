package com.example.libraryapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.libraryapp.models.Book

@Composable
fun BookListScreen(
    books: LiveData<List<Book>>,
    onDeleteClick: (Book) -> Unit,
    onListItemClick: (Book) -> Unit,
    onFabClick: () -> Unit
) {

    val bookList by books.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(Icons.Default.Add, contentDescription = "Add New Book")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding
        ) {
            items(bookList) { book ->
                BookListItem(
                    book = book,
                    onDeleteClick = { onDeleteClick(book) },
                    onItemClick = { onListItemClick(book) }
                )
            }
        }
    }
}

@Composable
fun BookListItem(
    book: Book,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = book.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "By ${book.author}", style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(onClick = onDeleteClick) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Book")
        }
    }
}