package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    val books = bookRepository.getBooks()

    fun addBook(book: Book) {
        books.add(book)
    }
}