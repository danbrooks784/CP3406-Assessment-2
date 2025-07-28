package com.example.cp3406assessment2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.views.EditBookScreen
import com.example.cp3406assessment2.ui.views.HomeScreen
import com.example.cp3406assessment2.ui.views.NewBookScreen
import com.example.cp3406assessment2.ui.views.SearchResultScreen
import com.example.cp3406assessment2.ui.views.SearchScreen
import com.example.cp3406assessment2.ui.views.ShelfScreen
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navigateToHome = { navController.navigate(Screens.HomeScreen.route) },
                navigateToSearch = { navController.navigate(Screens.SearchScreen.route) },
                navigateToShelf = { navController.navigate(Screens.ShelfScreen.route) },
                onEditButtonPressed = {
                    book: Book ->
                    navController.navigate(
                        "${Screens.EditBookScreen.route}/${Json.encodeToString(book)}"
                    )
                },
                onSearchButtonPressed = { navController.navigate(Screens.SearchScreen.route) }
            )
        }

        composable(Screens.SearchScreen.route) {
            SearchScreen(
                navigateToHome = { navController.navigate(Screens.HomeScreen.route) },
                navigateToSearch = { navController.navigate(Screens.SearchScreen.route) },
                navigateToShelf = { navController.navigate(Screens.ShelfScreen.route) },
                onSearch = {
                    query: String ->
                    navController.navigate("${Screens.SearchResultScreen.route}/${query}")
                }
            )
        }

        composable(
            "${Screens.SearchResultScreen.route}/{query}",
            arguments = listOf(
                navArgument("query") { type = NavType.StringType }
            )
        ) {
            SearchResultScreen(
                query = it.arguments?.getString("query").toString(),
                navigateBack = { navController.navigate(Screens.SearchScreen.route) },
                onAddButtonPressed = {
                    book: Book ->
                    navController.navigate(
                        "${Screens.NewBookScreen.route}/${Json.encodeToString(book)}"
                    )
                }
            )
        }

        composable(
            "${Screens.NewBookScreen.route}/{book}",
            arguments = listOf(
                navArgument("book") { type = NavType.StringType }
            )
        ) {
            NewBookScreen(
                book = Json.decodeFromString(it.arguments?.getString("book") ?: ""),
                navigateBack = { navController.navigate(Screens.SearchScreen.route) }
            )
        }

        composable(Screens.ShelfScreen.route) {
            ShelfScreen(
                navigateToHome = { navController.navigate(Screens.HomeScreen.route) },
                navigateToSearch = { navController.navigate(Screens.SearchScreen.route) },
                navigateToShelf = { navController.navigate(Screens.ShelfScreen.route) },
                onEditButtonPressed = {
                    book: Book ->
                    navController.navigate(
                        "${Screens.EditBookScreen.route}/${Json.encodeToString(book)}"
                    )
                }
            )
        }

        composable(
            "${Screens.EditBookScreen.route}/{book}",
            arguments = listOf(
                navArgument("book") { type = NavType.StringType }
            )
        ) {
            EditBookScreen(
                book = Json.decodeFromString(it.arguments?.getString("book") ?: ""),
                navigateBack = { navController.navigate(Screens.ShelfScreen.route) }
            )
        }
    }
}