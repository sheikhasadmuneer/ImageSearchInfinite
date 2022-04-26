package com.example.testinglibs

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentedTest {


    @Test
    fun test_isActivityInView() {
        // Context of the app under test.
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.edit_query)).check(matches(isDisplayed()))

    }

    @Test
    fun test_visibility_search_button() {
        // Context of the app under test.
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnSearch)).check(matches(isDisplayed()))

    }

    @Test
    fun test_visibility_initial_case() { // progress bar, error textviews should be hidden in initial case
        // Context of the app under test.
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.text_view_error)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.button_retry)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.text_view_empty)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }


    @Test
    fun test_perform_search_button_click() { // progress bar, error textviews should be hidden in initial case
        // Context of the app under test.
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnSearch)).perform(click());

    }

}