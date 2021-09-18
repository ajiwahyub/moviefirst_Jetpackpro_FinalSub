package com.ajijetpackpro.finalsub.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.ajijetpackpro.finalsub.R
import com.ajijetpackpro.finalsub.utils.IdlingResource
import org.junit.*
import org.junit.Assert.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }

    @Test
    fun loadListMovieListTvshow() {
        Espresso.onView(withId(R.id.recycleViewMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))

        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))

        Espresso.onView(withId(R.id.navigation_movie)).perform(ViewActions.click())
    }

    @Test
    fun testNavigationView() {
        Espresso.onView(withId(R.id.recycleViewMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(withText(R.string.tabmovie)).perform(ViewActions.click())
        Espresso.onView(withText(R.string.tabtvshos)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.navigation_movie)).perform(ViewActions.click())
    }

    @Test
    fun testFavoritandUnfavoritItem() {
        Espresso.onView(withId(R.id.recycleViewMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.floatingActionButton)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewTvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.floatingActionButton)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvFavMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.floatingActionButton)).perform(ViewActions.click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.navigation_favorite)).perform(ViewActions.click())
        Espresso.onView(withText(R.string.tabtvshos)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewFavTvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.floatingActionButton)).perform(ViewActions.click())
        Espresso.pressBack()

    }

    @Test
    fun testShowDetailMovie() {
        Espresso.onView(withId(R.id.recycleViewMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        Espresso.onView(withId(R.id.recycleViewMovie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
                    ViewActions.click()
                ))

        Espresso.onView(withId(R.id.tvDetailTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_userscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_rdate))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_bg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso.pressBack()
    }

    @Test
    fun testShowDetailTvshow() {
        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        Espresso.onView(withId(R.id.recycleViewTvshow))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
                    ViewActions.click()
                ))

        Espresso.onView(withId(R.id.tvDetailTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_overview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_userscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_rdate))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_bg))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
    }

}