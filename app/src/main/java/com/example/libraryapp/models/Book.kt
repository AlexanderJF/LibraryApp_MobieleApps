package com.example.libraryapp.models

import androidx.room.Entity


@Entity(tableName = "books")
data class Book(
    val id:Int,
    val title:String,
    val author:String
)
