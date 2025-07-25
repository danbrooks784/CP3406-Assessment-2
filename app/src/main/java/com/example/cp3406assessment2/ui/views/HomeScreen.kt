package com.example.cp3406assessment2.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onSearchButtonPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            BookTopAppBar() { }
        },

        bottomBar = {
            BookBottomAppBar(onSearchButtonPressed)
        },

        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
    Text(
        text = "Home screen",
        modifier = Modifier.padding(innerPadding))
    }
}