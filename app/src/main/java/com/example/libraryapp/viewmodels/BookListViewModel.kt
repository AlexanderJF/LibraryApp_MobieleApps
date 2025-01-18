package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.models.Book
import com.example.libraryapp.repositories.BookRepository
import com.example.libraryapp.repositories.IBookRepository
import kotlinx.coroutines.launch

class BookListViewModel(application: Application) : ViewModel() {
    private val repository = BookRepository(application)

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = repository.getAllBooks()

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.delete(book)
        }
    }
}
