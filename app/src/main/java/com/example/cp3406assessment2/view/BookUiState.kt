package com.example.cp3406assessment2.view

import com.example.cp3406assessment2.data.Book

data class BookUiState(
    val isLoading: Boolean = false,
    var books: MutableList<Book> = mutableListOf<Book>(),
    var searchResult: List<Book> = listOf<Book>(),
    val error: String? = null
)