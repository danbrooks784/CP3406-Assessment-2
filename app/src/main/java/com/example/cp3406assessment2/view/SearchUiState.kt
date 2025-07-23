package com.example.cp3406assessment2.view

import com.example.cp3406assessment2.data.Book

data class SearchUiState(
    val isLoading: Boolean = false,
    var result: List<Book> = listOf<Book>(),
    val error: String? = null
)