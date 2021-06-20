package com.haxos.foodityserver.rest.file

data class UploadFileResponse(
    val fileName: String,
    val fileDownloadUri: String,
    val fileType: String?,
    val size: Long
)
