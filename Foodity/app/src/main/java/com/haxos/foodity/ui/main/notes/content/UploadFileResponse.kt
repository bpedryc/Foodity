package com.haxos.foodity.ui.main.notes.content

data class UploadFileResponse(
        val fileName: String,
        val fileDownloadUri: String,
        val fileType: String?,
        val size: Long
)
