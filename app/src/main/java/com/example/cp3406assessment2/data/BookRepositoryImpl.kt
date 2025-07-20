package com.example.cp3406assessment2.data

import com.example.cp3406assessment2.data.api.BookAPI
import com.example.cp3406assessment2.data.api.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.toString

class BookRepositoryImpl(
    private val bookAPI: BookAPI,
    private val dispatcher: CoroutineDispatcher
) : BookRepository {
    override suspend fun getBooks(
        query: String
    ): NetworkResult<MutableList<Book>> {
        return withContext(dispatcher) {
            try {
                val response = bookAPI.searchBooks(query = query, maxResults = 10)
                if (response.isSuccessful) {
                    val items = response.body()!!.items
                    var books = mutableListOf<Book>()

                    for (item in items) {
                        books.add(item.volumeInfo)
                    }

                    NetworkResult.Success(books)
                } else {
                    NetworkResult.Error(
                        response.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}