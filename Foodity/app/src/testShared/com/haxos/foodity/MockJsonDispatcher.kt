package com.haxos.foodity

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class JsonDispatcher(
    private val endpoints: List<JsonEndpoint> = emptyList()
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        endpoints.forEach { endpoint ->
            val requestedPath = request.path!!
            if (requestedPath.contains(endpoint.path)) {
                return buildJsonResponse(endpoint.filename, endpoint.requestCode)
            }
        }
        return MockResponse().setResponseCode(404)
    }
}

class JsonEndpoint(
    val path: String,
    val filename: String,
    val requestCode: Int
)