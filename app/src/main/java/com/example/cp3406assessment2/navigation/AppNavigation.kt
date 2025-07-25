package com.example.cp3406assessment2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.ui.views.EditBookScreen
import com.example.cp3406assessment2.ui.views.HomeScreen
import com.example.cp3406assessment2.ui.views.SearchResultScreen
import com.example.cp3406assessment2.ui.views.SearchScreen
import com.example.cp3406assessment2.ui.views.SettingsScreen
import com.example.cp3406assessment2.ui.views.ShelfScreen
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.ShelfScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                onSearchButtonPressed = { navController.navigate(Screens.SearchScreen.route) }
            )
        }

        composable(Screens.SearchScreen.route) {
            SearchScreen(
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
                onAddButtonPressed = {
                    book: Book ->
                    navController.navigate(
                        "${Screens.EditBookScreen.route}/${Json.encodeToString(book)}"
                    )
                }
            )
        }

        composable(Screens.ShelfScreen.route) {
            ShelfScreen(
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
                book = Json.decodeFromString((it.arguments?.getString("book") ?: "")),
                onSaveButtonPressed = {
                    navController.popBackStack()
                },
                onDeleteButtonPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}