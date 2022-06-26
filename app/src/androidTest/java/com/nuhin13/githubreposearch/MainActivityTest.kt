package com.nuhin13.githubreposearch

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nuhin13.githubreposearch.data.FakeRepositoryItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_activity_text() {
        //testing purpose
        val repo = FakeRepositoryItem.repoList.items[4]
        Espresso.onView(ViewMatchers.withId(R.id.tvTest))
            .check(ViewAssertions.matches(ViewMatchers.withText(repo.name)))
    }
}