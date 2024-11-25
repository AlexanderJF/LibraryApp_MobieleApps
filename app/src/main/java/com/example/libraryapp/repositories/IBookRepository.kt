package com.example.libraryapp.repositories

import com.example.libraryapp.models.Book

interface IBookRepository {
    suspend fun getBooks():List<Book>
    suspend fun getBook(bookId:Int):Book
    suspend fun updateBook(book:Book)
    suspend fun insertBook(books:List<Book>)
    suspend fun deleteBook(book:Book)
}