package com.nuhin13.githubreposearch.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import java.util.concurrent.Flow


object PreferencesKeys {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sort")

    /*val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    val exampleCounterFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[EXAMPLE_COUNTER] ?: 0
        }*/
}