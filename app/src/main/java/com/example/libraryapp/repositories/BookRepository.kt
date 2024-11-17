package com.example.libraryapp.repositories

import android.app.Application
import com.example.libraryapp.models.Book
import com.example.libraryapp.services.BookDatabase

class BookRepository(application:Application):IBookRepository {
    private val dao = BookDatabase.getInstance(application).bookDao()

    override suspend fun getBooks(): List<Book> {
        return dao.getBooks()
    }

    override suspend fun getBook(bookId: Int): Book {
        return dao.getBook(bookId)
    }

    override suspend fun updateBook(book: Book) {
        return dao.update(book)
    }

    override suspend fun insertBook(book: Book) {
        return dao.insert(book)
    }

    override suspend fun deleteBook(book:Book) {
        return dao.delete(book)
    }
}