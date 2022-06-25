package com.nuhin13.githubreposearch.api

import android.content.Context
import com.nuhin13.githubreposearch.data.RepositoryList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/repositories")
    suspend fun getAllRepository(
        @Query("q") query: String,
        @Query("per_page") limit: Int,
        @Query("sort") sort: String
    ): Response<RepositoryList>

    companion object {
        const val BASE_URL = "https://api.github.com/"

        fun getInstance(context: Context): RetrofitService {
            return NetworkInterceptor.provideUnsplashApi(
                NetworkInterceptor.provideRetrofit(
                    NetworkInterceptor.provideOkHttp(context)
                )
            )
        }
    }
}