package com.nuhin13.githubreposearch.api

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllRepository(sort: String) = retrofitService.getAllRepository(
        query = "Android",
        limit = 50, sort = sort
    )
}