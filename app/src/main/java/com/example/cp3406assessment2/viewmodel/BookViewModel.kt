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

    private fun getBooks() {
        bookUiState.value = BookUiState(isLoading = true)
        viewModelScope.launch {
            when (val result = bookRepository.getBooks("test")) {
                is NetworkResult.Success -> {
                    bookUiState.update {
                        it.copy(isLoading = false, books = result.data)
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

    lateinit var search: String
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
        search = query
    }

    init {
        getBooks()
    }
}