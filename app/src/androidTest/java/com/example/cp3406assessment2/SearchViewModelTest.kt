package com.example.cp3406assessment2

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cp3406assessment2.data.Book
import com.example.cp3406assessment2.di.appModules
import com.example.cp3406assessment2.viewmodel.SearchViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin
import kotlin.getValue

@RunWith(AndroidJUnit4::class)
class SearchViewModelTest: Application() {
    private val searchViewModel by inject<SearchViewModel>()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }

    private var testBook1 = Book(
        "BymGltjHkbIC", "And Then There Were None", listOf("Agatha Christie"), "2010-10-14", 230
    )

    @Test
    @Throws(Exception::class)
    fun viewModelSearchQuery_returnsListOfBooks() = runBlocking {
        searchViewModel.searchQuery("and then there were none")
        val uiState = searchViewModel.searchUiState
        while (true) {
            if (!uiState.value.isLoading) {
                break
            }
        }
        assertEquals(uiState.value.result[0], testBook1)
    }

    @Test
    @Throws(Exception::class)
    fun viewModelDisplayResultInfo_returnsStringOfBookInfo() = runBlocking {
        val bookInfo = searchViewModel.displayResultInfo(testBook1)
        assertEquals(
            bookInfo,
            "${testBook1.title} (${testBook1.year.take(4)})" +
            "\nby ${testBook1.authors.joinToString(separator = ", ")}" +
            "\nPages: ${testBook1.totalPageCount}"
        )
    }
}