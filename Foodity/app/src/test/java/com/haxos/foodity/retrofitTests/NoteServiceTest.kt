package com.haxos.foodity.retrofitTests

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import com.haxos.foodity.data.model.*
import com.haxos.foodity.enqueueResponse
import com.haxos.foodity.retrofit.services.INoteElementService
import com.haxos.foodity.retrofit.LocalDateTimeAdapter
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class NoteServiceTest {
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val adapterFactory: RuntimeTypeAdapterFactory<NoteElement> = RuntimeTypeAdapterFactory.of(NoteElement::class.java, "@type")
        .registerSubtype(TextNoteElement::class.java)
        .registerSubtype(ListNoteElement::class.java)
        .registerSubtype(ImageNoteElement::class.java)

    private val gsonConfig: Gson = GsonBuilder()
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
    .registerTypeAdapterFactory(adapterFactory)
    .create()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gsonConfig))
        .build()
        .create(INoteElementService::class.java)

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun deserializeNoteElements() {
        mockWebServer.enqueueResponse("note-elements-200.json", 200)

        runBlocking {
            val response = api.getByNoteId(10)
            val actual = response.body()

            val expected = listOf(
                    ImageNoteElement(245, 1, "Beautiful photo",
                            "http://link.to.photo.com"),
                    ListNoteElement(258, 2, "Ingredients", mutableListOf(
                            ListNoteElementEntry(259, 1, "Cream"),
                            ListNoteElementEntry(264, 2, "Sugar"),
                            ListNoteElementEntry(265, 3, "Vanilla extract")
                    )),
                    TextNoteElement(243, 3, "Good to know",
                            "Some interesting facts here"),
                    TextNoteElement(242, 4, "Preparation",
                            "How to prepare")
            )

            assertEquals(expected, actual)
        }
    }
}