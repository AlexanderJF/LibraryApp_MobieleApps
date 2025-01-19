package com.example.libraryapp.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.models.Book
import com.example.libraryapp.repositories.BookRepository
import com.example.libraryapp.repositories.IBookRepository
import kotlinx.coroutines.launch

class BookListViewModel(application: Application) : ViewModel() {
    private val repository = BookRepository(application)

    private val _searchQuery = MutableLiveData("")
    private val _sortOrder = MutableLiveData("Title" to true)

    val books: LiveData<List<Book>> = repository.getAllBooks()

    val filteredAndSortedBooks: LiveData<List<Book>> = MediatorLiveData<List<Book>>().apply {
        addSource(books) { value = applyFilterAndSort() }
        addSource(_searchQuery) { value = applyFilterAndSort() }
        addSource(_sortOrder) { value = applyFilterAndSort() }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSortOrder(criteria: String) {
        _sortOrder.value = criteria to (_sortOrder.value?.second != true)
    }

    private fun applyFilterAndSort(): List<Book> {
        val searchQuery = _searchQuery.value.orEmpty().lowercase()
        val (criteria, ascending) = _sortOrder.value ?: ("Title" to true)

        return books.value.orEmpty()
            .filter {
                it.title.lowercase().contains(searchQuery) || it.author.lowercase().contains(searchQuery)
            }
            .sortedWith(
                when (criteria) {
                    "Author" -> if (ascending) compareBy { it.author } else compareByDescending { it.author }
                    else -> if (ascending) compareBy { it.title } else compareByDescending { it.title }
                }
            )
    }


    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.delete(book)
        }
    }
}
