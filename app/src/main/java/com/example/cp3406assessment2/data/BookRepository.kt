package com.example.cp3406assessment2.data

interface BookRepository {
    fun getBooks(): MutableList<Book>

    fun displayBook(book: Book): String {
        return "${book.title} (${book.year})" +
                "\nby ${book.author}" +
                "\nGenre: ${book.genre}" +
                "\nPages read: ${book.readPageCount} / ${book.totalPageCount} " +
                "(${book.calculatePercentageRead().toInt()}%)" +
                "\nRating: ${book.rating} / 5"
    }

    fun Book.calculatePercentageRead(): Double {
        return (readPageCount.toDouble() / totalPageCount.toDouble()) * 100
    }
}