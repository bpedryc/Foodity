package com.haxos.foodity

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haxos.foodity.RecyclerViewAssertions.itemViewMatches
import com.haxos.foodity.ui.main.notes.content.NoteFragment
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
class NoteFragmentTests {

    lateinit var mockWebServer: MockWebServer

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

    @Test
    fun loadNoteFragmentTest() {
        launchFragmentInHiltContainer<NoteFragment>(Bundle())
        Thread.sleep(400)

        onView(withId(R.id.recyclerview_noteelements))
            .check(itemViewMatches(0, R.id.note_name, withText("Panna cotta")))
            .check(itemViewMatches(3, R.id.element_text_title, withText("Good to know")))
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }
}
