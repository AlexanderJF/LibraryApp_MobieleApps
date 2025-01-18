package com.example.libraryapp.repositories

import androidx.lifecycle.LiveData
import com.example.libraryapp.models.Book

interface IBookRepository {
    fun getAllBooks(): LiveData<List<Book>>
    suspend fun getBook(bookId:Int): Book
    suspend fun insert(book: Book)
    suspend fun update(book: Book)
    suspend fun delete(book: Book)
}