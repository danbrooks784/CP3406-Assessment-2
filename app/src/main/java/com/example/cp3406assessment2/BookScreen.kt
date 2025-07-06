package com.example.cp3406assessment2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cp3406assessment2.viewmodel.BookViewModel
import com.example.cp3406assessment2.ui.ShelfScreen
import org.koin.androidx.compose.koinViewModel

enum class BookScreen {
    Home,
    Search,
    Shelf,
    Settings,
    NewBook
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookApp(
    viewModel: BookViewModel = koinViewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookshelf App") },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(BookScreen.Settings.name) }
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings button"
                        )
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navController.navigate(BookScreen.Home.name) }
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home button"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(BookScreen.Search.name) }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search button"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(BookScreen.Shelf.name) }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.List,
                            contentDescription = "Shelf button"
                        )
                    }
                }
            }
        },

        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(2.dp),
                onClick = { navController.navigate(BookScreen.NewBook.name) }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "New Book button"
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "New Book"
                )
            }
        },

        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BookScreen.Shelf.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = BookScreen.Home.name) {
                Text("Home screen")
            }

            composable(route = BookScreen.Search.name) {
                Text("Search screen")
            }

            composable(route = BookScreen.Shelf.name) {
                ShelfScreen(
                    books = viewModel.books
                )
            }

            composable(route = BookScreen.Settings.name) {
                Text("Settings screen")
            }

            composable(route = BookScreen.NewBook.name) {
                Text("Add book screen")
            }
        }
    }
}
