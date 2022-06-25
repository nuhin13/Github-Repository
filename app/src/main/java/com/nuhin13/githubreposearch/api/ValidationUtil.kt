package com.nuhin13.githubreposearch.api

import com.nuhin13.githubreposearch.data.RepositoryItem

object ValidationUtil {

    fun validateRepoItem(item: RepositoryItem): Boolean {
        if (item.name.isNotEmpty()) {
            return true
        }
        return false
    }
}