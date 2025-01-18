package com.example.libraryapp.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.libraryapp.models.Book
import com.example.libraryapp.services.BookDatabase

class BookRepository(application:Application):IBookRepository {

    private val dao = BookDatabase.getInstance(application).bookDao()

    override fun getAllBooks(): LiveData<List<Book>> {
        return dao.getAllBooks()
    }

    override suspend fun getBook(bookId: Int): Book {
        return  dao.getBookById(bookId)
    }

    override suspend fun insert(book: Book) {
        dao.insert(book)
    }

    override suspend fun update(book: Book) {
        dao.update(book)
    }

    override suspend fun delete(book: Book) {
        dao.delete(book)
    }
}