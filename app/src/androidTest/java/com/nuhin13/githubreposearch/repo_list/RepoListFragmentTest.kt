package com.nuhin13.githubreposearch.repo_list

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nuhin13.githubreposearch.MainActivity
import com.nuhin13.githubreposearch.R
import com.nuhin13.githubreposearch.data.FakeRepositoryItem
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class RepoListFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val position = 4
    private val repo = FakeRepositoryItem.repoList.items[position]

    @Test
    fun test_spinner_display() {
        onView(withId(R.id.spinnerSort)).check(matches(isDisplayed()))
    }

    @Test
    fun test_spinner_data() {
        onView(withId(R.id.spinnerSort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.spinnerSort)).check(matches(withSpinnerText(containsString("Stars"))))
    }

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.spinnerSort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.progressDialog)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_selectListItem() {
        onView(withId(R.id.spinnerSort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.progressDialog)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        /*onView(withId(R.id.recyclerview))
            .perform(actionOnItemAtPosition<MainViewHolder>(position, click()))

        onView(allOf(withId(R.id.tvName),withText("testUrl"))).perform(click());*/

        onView(withId(R.id.recyclerview))
            .perform(actionOnItemAtPosition<MainViewHolder>(position, click()))

        onView(withId(R.id.tvName)).check(matches(withText(repo.name)))
    }

    @Test
    fun test_backNavigation_toListFragment() {
        onView(withId(R.id.spinnerSort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.progressDialog)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.recyclerview))
            .perform(actionOnItemAtPosition<MainViewHolder>(position, click()))
        onView(withId(R.id.tvName)).check(matches(withText(repo.name)))

        pressBack()
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navList_DetailsFragment() {
        onView(withId(R.id.spinnerSort)).perform(click())
        onData(anything()).atPosition(0).perform(click())
        onView(withId(R.id.progressDialog)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerview)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.recyclerview))
            .perform(actionOnItemAtPosition<MainViewHolder>(position, click()))

        onView(withId(R.id.DetailsFragment)).perform(click())
    }

}