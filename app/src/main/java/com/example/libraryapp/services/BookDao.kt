package com.example.libraryapp.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.libraryapp.models.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun  getBook(bookId:Int):Book

    @Query("SELECT * FROM books ORDER BY title DESC")
    suspend fun  getBooks():List<Book>

    @Insert
    suspend fun insert(book:Book)

    @Update
    suspend fun update(book:Book)

    @Delete
    suspend fun delete(book:Book)
}