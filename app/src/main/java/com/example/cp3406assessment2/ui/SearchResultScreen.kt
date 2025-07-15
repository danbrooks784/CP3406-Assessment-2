package com.example.cp3406assessment2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SearchResultScreen(
    search: String
) {
    Column {
        Text("Search result screen")
        Text(search)
    }
}