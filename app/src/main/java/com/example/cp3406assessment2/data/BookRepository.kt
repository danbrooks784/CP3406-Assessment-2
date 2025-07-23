package com.example.cp3406assessment2.data

import com.example.cp3406assessment2.data.api.NetworkResult
import com.example.cp3406assessment2.data.database.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchByQuery(query: String): NetworkResult<List<Book>>

    fun getBooksFromDatabase(): Flow<List<Book>>

    suspend fun getFavouriteBooks(): Flow<List<Book>>

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(book: Book)

    suspend fun updateBook(book: Book)

    fun convertBookToBookEntity(book: Book): BookEntity

    fun convertBookEntityToBook(book: BookEntity): Book
}