package com.nuhin13.githubreposearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nuhin13.githubreposearch.repo_list.MainViewModel
import com.nuhin13.githubreposearch.api.MainRepository
import com.nuhin13.githubreposearch.api.RetrofitService
import com.nuhin13.githubreposearch.data.Owner
import com.nuhin13.githubreposearch.data.RepositoryItem
import com.nuhin13.githubreposearch.data.RepositoryList
import junit.framework.Assert.assertEquals
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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

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

    private val owner = Owner("http::/aaaaaa", "flutter")
    private val repo = RepositoryItem("test", "testUrl", owner, 123, "2022-09-29")

    private val listWithItem = RepositoryList(items = arrayListOf<RepositoryItem>(repo))

    @Test
    fun getRepositoryTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllRepository("stars"))
                .thenReturn(Response.success(listWithItem))
            mainViewModel.getTopRepository("stars")
            val result = mainViewModel.repoList.getOrAwaitValue()
            assertEquals(repo, result.items[0])
        }
    }

    @Test
    fun `empty repo list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllRepository("stars"))
                .thenReturn(Response.success(RepositoryList(arrayListOf<RepositoryItem>())))
            mainViewModel.getTopRepository("stars")
            val result = mainViewModel.repoList.getOrAwaitValue()
            assertEquals(arrayListOf<RepositoryItem>(), result.items)
        }
    }
}