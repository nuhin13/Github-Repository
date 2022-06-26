package com.nuhin13.githubreposearch.repo_details

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nuhin13.githubreposearch.data.FakeRepositoryItem
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test
import org.junit.runner.RunWith
import com.nuhin13.githubreposearch.R

@RunWith(AndroidJUnit4ClassRunner::class)
class RepoDetailsFragmentTest{

    @Test
    fun test_isRepoDetailsVisible() {
        val repo = FakeRepositoryItem.repoList.items[4]
        onView(withId(R.id.tvUserName)).check(matches(withText(repo.name)))
    }
}