package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.models.Book
import com.example.libraryapp.repositories.BookRepository
import kotlinx.coroutines.launch

class EditBookViewModel(application: Application, private val bookId: Int) : ViewModel() {
    private val repository = BookRepository(application)

    private val _book = MutableLiveData<Book?>()
    val book: LiveData<Book?> get() = _book

    init {
        getBook()
    }

    private fun getBook() {
        viewModelScope.launch {
            _book.value = repository.getBook(bookId)
        }
    }

    fun updateBook(updatedBook: Book) {
        viewModelScope.launch {
            repository.update(updatedBook)
        }
    }

    fun deleteBook() {
        viewModelScope.launch {
            _book.value?.let { repository.delete(it) }
        }
    }
}