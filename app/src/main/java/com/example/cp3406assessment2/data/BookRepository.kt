package com.example.cp3406assessment2.data

interface BookRepository {
    fun getBooks(): MutableList<Book>
}