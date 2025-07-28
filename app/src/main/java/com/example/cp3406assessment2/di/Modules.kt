package com.example.cp3406assessment2.di

import androidx.room.Room
import com.example.cp3406assessment2.data.api.BookAPI
import com.example.cp3406assessment2.data.database.BookDatabase
import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.data.BookRepositoryImpl
import com.example.cp3406assessment2.viewmodel.EditBookViewModel
import com.example.cp3406assessment2.viewmodel.HomeViewModel
import com.example.cp3406assessment2.viewmodel.SearchViewModel
import com.example.cp3406assessment2.viewmodel.ShelfViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single<BookRepository> {
        BookRepositoryImpl(get(), get(), get())
    }

    single {
        Dispatchers.IO
    }

    single{
        HomeViewModel(get())
    }

    single{
        SearchViewModel(get())
    }

    single{
        ShelfViewModel(get())
    }

    single{
        EditBookViewModel(get())
    }

    single {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl( "https://www.googleapis.com/books/v1/" )
            .build()
    }

    single {
        get<Retrofit>().create(BookAPI::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            BookDatabase::class.java,
            "book-database"
        ).build()
    }

    single {
        get<BookDatabase>().bookDao()
    }
}