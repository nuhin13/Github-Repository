package com.nuhin13.githubreposearch

import com.nuhin13.githubreposearch.api.MainRepository
import com.nuhin13.githubreposearch.api.RetrofitService
import com.nuhin13.githubreposearch.data.RepositoryItem
import com.nuhin13.githubreposearch.data.RepositoryList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun `get all repo test`() {
        runBlocking {
            Mockito.`when`(apiService.getAllRepository("Android", 50, "stars"))
                .thenReturn(Response.success(RepositoryList(arrayListOf<RepositoryItem>())))
            val response = mainRepository.getAllRepository("stars")
            assertEquals(arrayListOf<RepositoryItem>(), response.body()?.items)
        }
    }
}