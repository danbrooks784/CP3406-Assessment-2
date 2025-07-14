package com.example.cp3406assessment2.data

interface BookRepository {
    suspend fun getBooks(): NetworkResult<MutableList<Book>>
}