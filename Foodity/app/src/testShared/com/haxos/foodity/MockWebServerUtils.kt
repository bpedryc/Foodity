package com.haxos.foodity

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    enqueue(buildJsonResponse(fileName, code))
}

fun buildJsonResponse(fileName: String, code: Int) : MockResponse {
    val inputStream = MockWebServer::class.java.classLoader
        ?.getResourceAsStream("api-response/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
        ?: return MockResponse()

    return MockResponse()
        .setResponseCode(code)
        .setBody(source.readString(StandardCharsets.UTF_8))
}

