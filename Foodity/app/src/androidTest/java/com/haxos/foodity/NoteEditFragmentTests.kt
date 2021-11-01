package com.haxos.foodity

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haxos.foodity.RecyclerViewAssertions.itemViewMatches
import com.haxos.foodity.ui.main.notes.content.NoteEditFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NoteEditFragmentTests {

    private lateinit var mockWebServer: MockWebServer

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        mockWebServer.dispatcher = JsonDispatcher(listOf(
            JsonEndpoint("/notes", "note-id22-200.json", 200),
            JsonEndpoint("/elements", "note-elements-200.json", 200)
        ))
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }


    @Test
    fun addTextElementTest() {
        val fragmentArgs = Bundle()
        fragmentArgs.putLong("noteId", 22)
        launchFragmentInHiltContainer<NoteEditFragment>(fragmentArgs)

        Thread.sleep(400)

        onView(withId(R.id.action_note_addelement))
            .perform(click())

        onView(withText(R.string.dialog_notelement_title))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))

        onView(withText(R.string.dialog_noteelement_texttype))
            .perform(click())
        onView(withText(android.R.string.yes))
            .perform(click())

        onView(withId(R.id.recyclerview_noteelements))
            .check(itemViewMatches(5, R.id.element_text_title, withText("")))
    }
}
