package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.repositories.BookRepository
import com.example.libraryapp.repositories.IBookRepository
import kotlinx.coroutines.launch
import com.example.libraryapp.models.Book

class CatalogueViewModel(application: Application):ViewModel(){
    private val bookRepository:IBookRepository =BookRepository(application)
    var books:List<Book>? by mutableStateOf(null)
    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            books = bookRepository.getBooks()
        }
    }
}