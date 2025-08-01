package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.ui.state.ShelfUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ShelfViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    val shelfUiState: StateFlow<ShelfUiState> =
        bookRepository.getBooksFromDatabase()
            .map {
                ShelfUiState(books = it as MutableList<Book>)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = ShelfUiState()
            )

    fun displayBookInfo(book: Book): String {
        return "${book.title} (${book.year.take(4)})" +
                "\nby ${
                    if (book.authors.isEmpty()) "unknown author"
                    else (book.authors.joinToString(separator = ", "))
                }" +
                "\nPages read: ${book.readPageCount} / ${book.totalPageCount} " +
                "(${((book.readPageCount.toDouble() / book.totalPageCount.toDouble()) * 100).toInt()}%)" +
                "\nRating: ${book.rating} / 5"
    }
}