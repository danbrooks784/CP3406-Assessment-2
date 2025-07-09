package com.example.cp3406assessment2.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens("Home")
    object SearchScreen : Screens("Search")
    object SearchResultScreen : Screens("SearchResult")
    object ShelfScreen : Screens("Shelf")
    object NewBookScreen : Screens("NewBook")
    object SettingsScreen : Screens("Settings")
}
