package com.nuhin13.githubreposearch

import com.google.gson.Gson
import com.nuhin13.githubreposearch.api.RetrofitService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: RetrofitService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://api.github.com/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(RetrofitService::class.java)
    }


    @Test
    fun `get all movie api test`() {
        runBlocking {
             val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getAllRepository("Android", 50, "stars")
            //val request = mockWebServer.takeRequest()
            //assertEquals("search/repositories", request.path)
            assertEquals(true, response.body()?.items?.isNotEmpty() == true)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}