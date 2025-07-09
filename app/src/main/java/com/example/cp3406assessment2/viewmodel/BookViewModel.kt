package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    val books = bookRepository.getBooks()

    lateinit var search: String
    lateinit var searchType: String

    fun addBook(book: Book) {
        books.add(book)
    }

    fun searchQuery(query: String, filter: String) {
        search = query
        searchType = filter
    }
}