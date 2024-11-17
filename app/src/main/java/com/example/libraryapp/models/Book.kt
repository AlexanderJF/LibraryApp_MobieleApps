package com.example.libraryapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "books")
@Serializable
data class Book(
    @PrimaryKey
    val id:Int,
    val title:String,
    val author:String
)
