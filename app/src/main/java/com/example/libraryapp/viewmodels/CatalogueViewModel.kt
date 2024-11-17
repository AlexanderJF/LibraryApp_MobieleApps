package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.repositories.BookRepository
import com.example.libraryapp.repositories.IBookRepository
import kotlinx.coroutines.launch

class CatalogueViewModel(application: Application):ViewModel(){
    private val bookRepository:IBookRepository =BookRepository(application)
    init {
        getBooks()
    }

    fun getBooks(){
        viewModelScope.launch {

        }
    }
}