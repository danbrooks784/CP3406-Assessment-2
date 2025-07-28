package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.ui.state.HomeUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        bookRepository.getUnfinishedBooks()
            .map {
                HomeUiState(unfinishedBooks = it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState()
            )

    fun displayBookInfo(book: Book): String {
        return "${book.title} (${book.year.take(4)})" +
                "\nby ${
                    if (book.authors.isEmpty()) "unknown author"
                    else (book.authors.joinToString(separator = ", "))
                }" +
                "\nPages read: ${book.readPageCount} / ${book.totalPageCount} " +
                "(${((book.readPageCount.toDouble() / book.totalPageCount.toDouble()) * 100).toInt()}%)"
    }
}
