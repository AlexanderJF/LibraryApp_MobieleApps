package com.example.libraryapp.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.libraryapp.models.Book


@Database(entities = [Book::class], version = 1)
abstract class BookDatabase :RoomDatabase() {
    abstract fun bookDao():BookDao
    companion object {
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "books_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}