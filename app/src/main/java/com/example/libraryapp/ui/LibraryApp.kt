package com.example.libraryapp.ui

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.libraryapp.R
import com.example.libraryapp.ui.screens.AddNewBookScreen
import com.example.libraryapp.ui.screens.BookListScreen
import com.example.libraryapp.ui.screens.EditBookScreen
import com.example.libraryapp.viewmodels.AddNewBookViewModel
import com.example.libraryapp.viewmodels.BookListViewModel
import com.example.libraryapp.viewmodels.EditBookViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (backStackEntry?.destination?.route) {
        "list" -> stringResource(id = R.string.app_name)
        "add" -> stringResource(id = R.string.add_book)
        "edit/{bookId}" -> stringResource(id = R.string.edit_book)
        else -> stringResource(id = R.string.app_name)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentScreen) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_button)
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // List Screen
            composable("list") {
                val context = LocalContext.current
                val viewModel: BookListViewModel = viewModel {
                    BookListViewModel(context.applicationContext as Application)
                }

                BookListScreen(
                    books = viewModel.filteredAndSortedBooks,
                    onDeleteClick = { viewModel.deleteBook(it) },
                    onListItemClick = { book -> navController.navigate("edit/${book.id}") },
                    onFabClick = { navController.navigate("add") },
                    onSearchQueryChange = { viewModel.setSearchQuery(it) },
                    onSortOrderChange = { viewModel.setSortOrder(it) }
                )
            }


            composable("add") {
                val context = LocalContext.current
                val viewModel: AddNewBookViewModel = viewModel {
                    AddNewBookViewModel(context.applicationContext as Application)
                }

                AddNewBookScreen(
                    onSaveClick = { book ->
                        viewModel.addBook(book)
                        navController.navigateUp()
                    },
                    onCancelClick = { navController.navigateUp() }
                )
            }


            composable(
                route = "edit/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.IntType })
            ) { backStackEntry ->
                val context = LocalContext.current
                val bookId = backStackEntry.arguments?.getInt("bookId") ?: return@composable

                val viewModel: EditBookViewModel = viewModel {
                    EditBookViewModel(context.applicationContext as Application,bookId)
                }

                EditBookScreen(
                    book = viewModel.book,
                    onSaveClick = { updatedBook ->
                        viewModel.updateBook(updatedBook)
                        navController.navigateUp()
                    },
                    onDeleteClick = {
                        viewModel.deleteBook()
                        navController.navigateUp()
                    },
                    onCancelClick = { navController.navigateUp() }
                )
            }
        }
    }
}