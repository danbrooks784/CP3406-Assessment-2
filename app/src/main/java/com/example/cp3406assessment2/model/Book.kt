package com.example.cp3406assessment2.model

class Book(
    val title: String = "Title",
    val author: String = "Author",
    val genre: String = "Genre",
    val year: Int = 1900,
    val totalPageCount: Int = 0,
    var readPageCount: Int = 0,
    var rating: Int = 0
) {
    fun calculatePercentageRead(book: Book): Double {
        return (book.readPageCount.toDouble() / book.totalPageCount.toDouble()) * 100
    }

    fun displayBook(book: Book): String {
        return "${book.title} (${book.year})\nby ${book.author}\nGenre: ${book.genre}" +
                "\nPages read: ${book.readPageCount} / ${book.totalPageCount}" +
                " (${book.calculatePercentageRead(book).toInt()}%)\nRating: ${book.rating} / 5"
    }
}