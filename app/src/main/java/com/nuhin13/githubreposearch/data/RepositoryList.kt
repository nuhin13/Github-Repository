package com.nuhin13.githubreposearch.data

import java.io.Serializable

data class RepositoryList(
    val items: ArrayList<RepositoryItem>,
): Serializable

data class RepositoryItem(
    val description: String?,
    val name: String,
    val owner: Owner,
    val stargazers_count: Int,
    val updated_at: String
) : Serializable

data class Owner(
    val avatar_url: String,
    val login: String
) : Serializable




