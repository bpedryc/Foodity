package com.haxos.foodityserver.rest.file

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.ws.rs.core.HttpHeaders

@RestController
class FileController(
    @Autowired val fileStorageService: FileStorageService
) {
    private val logger = LoggerFactory.getLogger(FileController::class.java)

    @PostMapping("/file")
    fun uploadFile(@RequestBody file: MultipartFile) : UploadFileResponse {
        val fileName: String = fileStorageService.storeFile(file)

        val fileDownloadUri: String = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/file/")
            .path(fileName)
            .toUriString()

        return UploadFileResponse(fileName, fileDownloadUri, file.contentType, file.size)
    }

    @PostMapping("/files")
    fun uploadMutlipleFiles(@RequestBody files: List<MultipartFile>): List<UploadFileResponse> {
        return files.map { file -> uploadFile(file) }
    }

    @GetMapping("/file/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String, request: HttpServletRequest): ResponseEntity<Resource> {
        val resource: Resource = fileStorageService.loadFileAsResource(fileName)

        return try {
            val contentType : String = request.servletContext.getMimeType(resource.file.absolutePath)
                ?: "application/octet-stream"

            ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename + "\"")
                .body(resource)
        } catch (exception: IOException) {
            logger.info("Could not determine file type.")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}