package com.example.cp3406assessment2.view

import com.example.cp3406assessment2.data.Book

data class ShelfUiState(
    var books: List<Book> = listOf<Book>()
)