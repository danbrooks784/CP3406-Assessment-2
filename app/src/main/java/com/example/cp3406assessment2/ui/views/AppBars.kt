package com.example.cp3406assessment2.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTopAppBar(
    onSettingsButtonPressed: () -> Unit
) {
    TopAppBar(
        title = { Text("Bookshelf App") },
        actions = {
            IconButton(
                onClick = { onSettingsButtonPressed }
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings button"
                )
            }
        }
    )
}

@Composable
fun BookBottomAppBar(
    onHomeButtonPressed: () -> Unit = {},
    onSearchButtonPressed: () -> Unit = {},
    onShelfButtonPressed: () -> Unit = {}
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { onHomeButtonPressed }
            ) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home button"
                )
            }
            IconButton(
                onClick = { onSearchButtonPressed }
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search button"
                )
            }
            IconButton(
                onClick = { onShelfButtonPressed }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Shelf button"
                )
            }
        }
    }
}