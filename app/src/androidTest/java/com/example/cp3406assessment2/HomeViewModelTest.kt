package com.example.cp3406assessment2

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.di.appModules
import com.example.cp3406assessment2.viewmodel.HomeViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin
import kotlin.getValue
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest: Application() {
    private val homeViewModel by inject<HomeViewModel>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }

    private var testBook = Book(
        "T4rwTkWd4NcC", "The Fellowship of the Ring", listOf("John Ronald Reuel Tolkien"),
        "2003-09-05", 436, 0, 0, "Still need to read", false
    )

    @Test
    @Throws(Exception::class)
    fun viewModelDisplayBookInfo_returnsStringOfBookInfo() = runBlocking {
        val bookInfo = homeViewModel.displayBookInfo(testBook)
        assertEquals(
            bookInfo,
            "${testBook.title} (${testBook.year.take(4)})" +
            "\nby ${testBook.authors.joinToString(separator = ", ")}" +
            "\nPages read: ${testBook.readPageCount} / ${testBook.totalPageCount} " +
            "(${((testBook.readPageCount.toDouble() / testBook.totalPageCount.toDouble()) * 100).toInt()}%)"
        )
    }
}