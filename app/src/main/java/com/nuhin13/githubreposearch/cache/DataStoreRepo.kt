package com.nuhin13.githubreposearch.cache

import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {
    suspend fun saveSortPosition(sortPosition: Int)
    suspend fun getSortPosition(): Flow<Int>
}