package com.example.libraryapp.ui

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.libraryapp.R
import com.example.libraryapp.models.Book
import com.example.libraryapp.ui.screens.BookDetailsScreen
import com.example.libraryapp.ui.screens.CatalogueView
import com.example.libraryapp.viewmodels.BookDetailsViewModel
import com.example.libraryapp.viewmodels.CatalogueViewModel
import kotlinx.serialization.Serializable


@Serializable
object Catalogue

@Serializable
data class Details(val bookId: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryApp(navController: NavHostController = rememberNavController()){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.substringBefore('/') ?: Catalogue::class.qualifiedName
    val title = when (currentScreen) {
        Catalogue::class.qualifiedName -> stringResource(id = R.string.app_name)
        else -> ""
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
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
                })
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Catalogue,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            composable<Catalogue>{
                val context = LocalContext.current
                val viewModel: CatalogueViewModel = viewModel() {
                    CatalogueViewModel(context.applicationContext as Application)
            }
                //Voor navigatie, aanpassen wanneer crud scherm(en) zijn toegevoegd
                val onListItemClick = { book: Book ->
                    navController.navigate(Details(book.id))
                }

                CatalogueView(
                    books= viewModel.books,
                    retryAction = {viewModel.getBooks()},
                    onListItemClick = onListItemClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<Details> {backStackEntry ->
                val args: Details = backStackEntry.toRoute<Details>()

                val context = LocalContext.current
                val viewModel: BookDetailsViewModel = viewModel() {
                    BookDetailsViewModel(args.bookId, context.applicationContext as Application)
                }
                BookDetailsScreen(
                    book=viewModel.book
                )
            }

        }
    }
}
