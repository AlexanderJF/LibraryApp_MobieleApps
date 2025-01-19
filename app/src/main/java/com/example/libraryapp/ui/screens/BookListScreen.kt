package com.example.libraryapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.libraryapp.models.Book

@Composable
fun BookListScreen(
    books: LiveData<List<Book>>,
    onDeleteClick: (Book) -> Unit,
    onListItemClick: (Book) -> Unit,
    onFabClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSortOrderChange: (String) -> Unit
) {

    val bookList by books.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(Icons.Default.Add, contentDescription = "Add New Book")
            }
        }
    ) { padding ->
        Column {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    onSearchQueryChange(it)
                },
                label = { Text("Search by Title or Author") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Title",
                    modifier = Modifier
                        .clickable { onSortOrderChange("Title") }
                        .weight(3f),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Author",
                    modifier = Modifier
                        .clickable { onSortOrderChange("Author") }
                        .weight(2f),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "ISBN",
                    modifier= Modifier.weight(2f),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            HorizontalDivider()

            LazyColumn(
                contentPadding = padding
            ) {
                itemsIndexed(bookList) { index, book ->
                    val itemBackgroundColor= if (index%2==0){
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.1f)
                    }
                    else{
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
                    }
                    BookListItem(
                        book = book,
                        onDeleteClick = { onDeleteClick(book) },
                        onItemClick = { onListItemClick(book) },
                        backgroundColor = itemBackgroundColor
                    )
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}


@Composable
fun BookListItem(
    book: Book,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit,
    backgroundColor:Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable(onClick = onItemClick)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = book.title,
            modifier = Modifier.weight(3f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = book.author,
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = book.isbn ?: "N/A",
            modifier = Modifier.weight(2f),
            style = MaterialTheme.typography.bodyMedium
        )
        IconButton(onClick = onDeleteClick, modifier = Modifier.weight(1f)) {
            Icon(Icons.Default.Delete, contentDescription = "Delete Book")
        }

    }
}