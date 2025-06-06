package com.example.cp3406assessment2.model

class Book(
    val title: String = "Title",
    val author: String = "Author",
    val genre: String = "Genre",
    val year: Int = 1900,
    val totalPageCount: Int = 0,
    var readPageCount: Int = 0,
    var rating: Int = 0
)