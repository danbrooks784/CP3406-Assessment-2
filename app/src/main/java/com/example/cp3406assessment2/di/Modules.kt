package com.example.cp3406assessment2.di

import com.example.cp3406assessment2.data.BookRepository
import com.example.cp3406assessment2.data.BookRepositoryImpl
import com.example.cp3406assessment2.viewmodel.BookViewModel
import org.koin.dsl.module

val appModules = module {
    single<BookRepository> {
        BookRepositoryImpl()
    }

    single{ BookViewModel(get()) }
}