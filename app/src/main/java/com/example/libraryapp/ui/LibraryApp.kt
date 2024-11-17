package com.example.libraryapp.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable


@Serializable
object Home

@Serializable
data class Details(val bookId: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryApp(){

}