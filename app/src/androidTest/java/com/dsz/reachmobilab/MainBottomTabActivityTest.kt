package com.dsz.reachmobilab

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.dsz.reachmobilab.ui.team.TeamDetailActivity
import org.junit.Assert.*
import org.junit.Test

class MainBottomTabActivityTest {

    @Test
    fun test_isActivityInView() {

        val activityScenario = ActivityScenario.launch(MainBottomTabActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}