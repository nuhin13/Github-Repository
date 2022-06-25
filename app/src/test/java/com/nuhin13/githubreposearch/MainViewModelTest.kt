package com.nuhin13.githubreposearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nuhin13.githubreposearch.repo_list.MainViewModel
import com.nuhin13.githubreposearch.api.MainRepository
import com.nuhin13.githubreposearch.api.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(apiService)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getAllMoviesTest() {
        runBlocking {
            /*Mockito.`when`(mainRepository.getAllMovies())
                .thenReturn(Response.success(listOf<Movie>(Movie("movie", "", "new"))))
            mainViewModel.getAllMovies()
            val result = mainViewModel.movieList.getOrAwaitValue()
            assertEquals(listOf<Movie>(Movie("movie", "", "new")), result)*/
        }
    }


    @Test
    fun `empty movie list test`() {
        runBlocking {
            /*Mockito.`when`(mainRepository.getAllMovies())
                .thenReturn(Response.success(listOf<Movie>()))
            mainViewModel.getAllMovies()
            val result = mainViewModel.movieList.getOrAwaitValue()
            assertEquals(listOf<Movie>(), result)*/
        }
    }

}