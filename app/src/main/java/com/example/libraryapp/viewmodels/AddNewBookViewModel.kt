package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.models.Book
import com.example.libraryapp.repositories.BookRepository
import kotlinx.coroutines.launch

class AddNewBookViewModel(application: Application) : ViewModel() {
    private val repository = BookRepository(application)

    fun addBook(book: Book) {
        viewModelScope.launch {
            repository.insert(book)
        }
    }
}