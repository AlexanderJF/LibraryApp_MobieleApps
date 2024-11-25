package com.example.libraryapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.ForeignKey
import com.example.libraryapp.models.Book
import org.w3c.dom.Text

@Composable
fun CatalogueView(
    books:List<Book>?,
    retryAction:()->Unit,
    onListItemClick:(Book)->Unit,
    modifier: Modifier=Modifier
) {
    if(books!=null)
        Column(modifier = Modifier) {
            Box(modifier = Modifier.weight(weight = 0.9f, fill = true)) {
                LazyColumn {
                    items(books) { book ->
                        BookEntry(
                            item = book,
                            onItemClick = onListItemClick
                        )
                    }
                }
            }
        }
}
    @Composable
    fun BookEntry(
        item: Book,
        onItemClick: (Book) -> Unit

    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { onItemClick(item) }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.id.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = item.author,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
