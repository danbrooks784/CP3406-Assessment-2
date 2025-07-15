package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.data.NetworkResult
import com.example.cp3406assessment2.view.BookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
    TODO: Update book repository instead of ui state!
 */

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    val bookUiState = MutableStateFlow(BookUiState())

    lateinit var bookToEdit: Book

    fun addBook(book: Book) {
        bookUiState.value.books.add(book)
    }

    fun deleteBook(book: Book) {
        bookUiState.value.books.remove(book)
    }

    fun retrieveBooks(): List<Book> {
        return bookUiState.value.books
    }

    fun searchQuery(query: String) {
        bookUiState.value = BookUiState(isLoading = true)
        viewModelScope.launch {
            when (val result = bookRepository.getBooks(query)) {
                is NetworkResult.Success -> {
                    bookUiState.update {
                        it.copy(isLoading = false, searchResult = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    bookUiState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}