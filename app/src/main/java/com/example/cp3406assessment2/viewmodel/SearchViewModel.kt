package com.example.cp3406assessment2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.data.api.NetworkResult
import com.example.cp3406assessment2.ui.state.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    val searchUiState = MutableStateFlow(SearchUiState())

    fun searchQuery(query: String) {
        searchUiState.value = SearchUiState(isLoading = true)
        viewModelScope.launch {
            when (val result = bookRepository.searchByQuery(query)) {
                is NetworkResult.Success -> {
                    searchUiState.update {
                        it.copy(isLoading = false, result = result.data)
                    }
                }
                is NetworkResult.Error -> {
                    searchUiState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun displayResultInfo(result: Book): String {
        return "${result.title} (${result.year.take(4)})" +
                "\nby ${
                    if (result.authors.isEmpty()) "unknown author"
                    else (result.authors.joinToString(separator = ", "))
                }" +
                "\nPages: ${result.totalPageCount}"
    }
}