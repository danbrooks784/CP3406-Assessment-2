package com.example.cp3406assessment2

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.di.appModules
import com.example.cp3406assessment2.viewmodel.ShelfViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin
import kotlin.getValue
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class ShelfViewModelTest: Application() {
    private val shelfViewModel by inject<ShelfViewModel>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }

    private var testBook = Book(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230,
        230, 5, "Great mystery novel", true
    )

    @Test
    @Throws(Exception::class)
    fun viewModelDisplayBookInfo_returnsStringOfBookInfo() = runBlocking {
        val bookInfo = shelfViewModel.displayBookInfo(testBook)
        assertEquals(
            bookInfo,
            "${testBook.title} (${testBook.year.take(4)})" +
            "\nby ${testBook.authors.joinToString(separator = ", ")}" +
            "\nPages read: ${testBook.readPageCount} / ${testBook.totalPageCount} " +
            "(${((testBook.readPageCount.toDouble() / testBook.totalPageCount.toDouble()) * 100).toInt()}%)" +
            "\nRating: ${testBook.rating} / 5"
        )
    }
}