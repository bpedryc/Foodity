package com.haxos.foodity.ui.main.notes.content

import android.content.ContentResolver
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import okio.IOException
import okio.source

class ContentUriRequestBody(
        val filename: String,
        private val contentResolver: ContentResolver,
        private val contentUri: Uri
) : RequestBody() {

    override fun contentType(): MediaType? {
        val contentType = contentResolver.getType(contentUri)
        return contentType?.toMediaTypeOrNull()
    }

    override fun writeTo(sink: BufferedSink) {
        val inputStream = contentResolver.openInputStream(contentUri)
                ?: throw IOException("Error while trying to read file from URI")

        inputStream.source().use {
            sink.writeAll(it)
        }
    }

}