package com.example.libraryapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.libraryapp.R
import com.example.libraryapp.models.Book

@Composable
fun BookDetailsScreen(
    book: Book?,
    modifier: Modifier=Modifier
){
    if (book!=null)
        Column {
            Text(book.title)
            Text(book.author)
        }
}