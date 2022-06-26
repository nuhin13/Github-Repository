package com.nuhin13.githubreposearch

import com.nuhin13.githubreposearch.api.ValidationUtil
import com.nuhin13.githubreposearch.data.Owner
import com.nuhin13.githubreposearch.data.RepositoryItem
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationUtilTest {

    @Test
    fun validateItemTest() {
        val owner = Owner("http::/aaaaaa", "flutter")
        val repo = RepositoryItem("test", "testUrl", owner, 123, "2022-09-29")
        assertEquals(true, ValidationUtil.validateRepoItem(repo))
    }

    @Test
    fun validateItemEmptyTest() {
        val owner = Owner("http::/aaaaaa", "")
        val repo = RepositoryItem("test", "testUrl", owner, 123, "")

        assertEquals(false, ValidationUtil.validateRepoItem(repo))
    }

}