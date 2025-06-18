package com.example.cp3406assessment2.di

import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.data.BookRepositoryImpl
import com.example.cp3406assessment2.viewmodel.BookViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single<BookRepository> {
        BookRepositoryImpl()
    }

    single{
        BookViewModel(get())
    }

    single {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl( "https://openlibrary.org/search.json" )
            .build()
    }
}