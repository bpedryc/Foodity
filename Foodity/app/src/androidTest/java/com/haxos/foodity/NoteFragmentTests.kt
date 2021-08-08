package com.haxos.foodity

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haxos.foodity.ui.main.notes.content.NoteFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NoteFragmentTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testEventFragment() {
        val fragmentArgs = Bundle()
        launchFragmentInHiltContainer<NoteFragment>(fragmentArgs)

        onView(withId(R.id.text)).check(matches(withText("Hello World!")))
    }

}