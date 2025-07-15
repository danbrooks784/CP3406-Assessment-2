package com.example.cp3406assessment2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cp3406assessment2.navigation.Screens
import com.example.cp3406assessment2.ui.EditBookScreen
import com.example.cp3406assessment2.ui.HomeScreen
import com.example.cp3406assessment2.ui.NewBookScreen
import com.example.cp3406assessment2.ui.SearchResultScreen
import com.example.cp3406assessment2.ui.SearchScreen
import com.example.cp3406assessment2.ui.SettingsScreen
import com.example.cp3406assessment2.viewmodel.BookViewModel
import com.example.cp3406assessment2.ui.ShelfScreen
import org.koin.androidx.compose.koinViewModel

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
                        onClick = { navController.navigate(Screens.SettingsScreen.route) }
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
                        onClick = { navController.navigate(Screens.HomeScreen.route) }
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home button"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(Screens.SearchScreen.route) }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search button"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(Screens.ShelfScreen.route) }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.List,
                            contentDescription = "Shelf button"
                        )
                    }
                }
            }
        },

        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.HomeScreen.route) {
                HomeScreen()
            }

            composable(Screens.SearchScreen.route) {
                SearchScreen(
                    onSearch = {
                        query: String -> viewModel.searchQuery(query)
                        navController.navigate(Screens.SearchResultScreen.route)
                    }
                )
            }

            composable(Screens.SearchResultScreen.route) {
                SearchResultScreen(
                    uiState = viewModel.bookUiState.collectAsStateWithLifecycle()
                )
            }

            composable(Screens.ShelfScreen.route) {
                ShelfScreen(
                    books = viewModel.retrieveBooks(),
                    onEditButtonPressed = {
                        viewModel.bookToEdit = it
                        navController.navigate(Screens.EditBookScreen.route)
                    }
                )
            }

            composable(Screens.NewBookScreen.route) {
                NewBookScreen(
                    onAddButtonPressed = {
                        viewModel.addBook(it)
                        navController.popBackStack()
                    }
                )
            }

            composable(Screens.EditBookScreen.route) {
                EditBookScreen(
                    book = viewModel.bookToEdit,
                    onSaveButtonPressed = {
                        viewModel.deleteBook(viewModel.bookToEdit)
                        viewModel.addBook(it)
                        navController.popBackStack()
                    },
                    onDeleteButtonPressed = {
                        viewModel.deleteBook(viewModel.bookToEdit)
                        navController.popBackStack()
                    }
                )
            }

            composable(Screens.SettingsScreen.route) {
                SettingsScreen()
            }
        }
    }
}
