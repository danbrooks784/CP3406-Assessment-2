package com.example.cp3406assessment2.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int
    ): Response<APIResponse>
}