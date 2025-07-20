package com.example.cp3406assessment2.data

import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.data.api.NetworkResult

interface BookRepository {
    suspend fun getBooks(
        query: String
    ): NetworkResult<MutableList<Book>>
}