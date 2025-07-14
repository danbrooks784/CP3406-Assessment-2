package com.example.cp3406assessment2.view

import com.example.cp3406assessment2.data.Book

data class BookUiState(
    val isLoading: Boolean = false,
    val books: MutableList<Book> = mutableListOf<Book>(),
    val error: String? = null
)