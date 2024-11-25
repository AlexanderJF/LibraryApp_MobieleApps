package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.models.Book
import com.example.libraryapp.repositories.BookRepository
import com.example.libraryapp.repositories.IBookRepository
import kotlinx.coroutines.launch

class BookDetailsViewModel(bookId:Int,application: Application):ViewModel() {
    private val bookRepository:IBookRepository=BookRepository(application)
    var book: Book? by mutableStateOf(null)

    init {
        getBook(bookId)
    }
    fun getBook(bookId: Int){
        viewModelScope.launch {
            book=bookRepository.getBook(bookId)
        }
    }
}