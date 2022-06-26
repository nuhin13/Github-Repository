package com.nuhin13.githubreposearch.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val DATASTORE_NAME = "DATASTORE_GITHUB"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreImpl(private val context: Context) : DataStoreRepo {
    companion object {
        val SORT = intPreferencesKey("sort")
    }

    override suspend fun saveSortPosition(position: Int) {
        context.datastore.edit { pref ->
            pref[SORT] = position
        }
    }

    override suspend fun getSortPosition() = context.datastore.data.map { pref ->
        pref[SORT] ?: 0
    }
}