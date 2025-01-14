package com.example.libraryapp.repositories

import com.example.libraryapp.models.Book

interface IBookRepository {
    suspend fun getAllBooks(): List<Book>
    suspend fun getBook(bookId:Int): Book
    suspend fun insert(book: Book)
    suspend fun update(book: Book)
    suspend fun delete(book: Book)
}