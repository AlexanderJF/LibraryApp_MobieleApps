package com.example.libraryapp.repositories

import android.app.Application
import com.example.libraryapp.models.Book
import com.example.libraryapp.services.BookDatabase

class BookRepository(application:Application):IBookRepository {
    private val dao = BookDatabase.getInstance(application).bookDao()

    override suspend fun getBooks(): List<Book> {
        if (dao.getBooks().isEmpty()) {
            seed()
        }
        return dao.getBooks()
    }

    override suspend fun getBook(bookId: Int): Book {
        return dao.getBook(bookId)
    }

    override suspend fun updateBook(book: Book) {
        return dao.update(book)
    }

    override suspend fun insertBook(books: List<Book>) {
        return dao.insert(books)
    }

    override suspend fun deleteBook(book:Book) {
        return dao.delete(book)
    }

    private suspend fun seed(){
        dao.insert(
            listOf(
                Book(
                    id=1,
                    title = "Good Omens",
                    author= "Terry Pratchet & Neill Gaiman"
                ),
                Book(
                    id=2,
                    title = "Sapiens",
                    author= "Yuval Noah Harrari"
                )
            )
        )
    }


}