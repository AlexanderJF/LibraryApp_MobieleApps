package com.example.libraryapp.repositories

import com.example.libraryapp.models.Book

class BookRepository:IBookRepository {
    override suspend fun getAllBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(bookId: Int): Book {
        TODO("Not yet implemented")
    }

    override suspend fun insert(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun update(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(book: Book) {
        TODO("Not yet implemented")
    }
}