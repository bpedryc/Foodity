package com.haxos.foodityserver.rest.file

import com.haxos.foodityserver.FileStorageConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileStorageService (
    @Autowired val fileStorageConfiguration: FileStorageConfiguration
) {
    private var fileStorageLocation: Path = Paths.get(fileStorageConfiguration.uploadDir)
        .toAbsolutePath().normalize()

    init {
        try {
            Files.createDirectories(fileStorageLocation)
        } catch (ex: Exception) {
            throw FileStorageException("Could not create the directory where the uploaded files will be stored.", ex)
        }
    }

    fun storeFile(file: MultipartFile) : String {
        val fileName : String = file.originalFilename?.let { StringUtils.cleanPath(it) }
            ?: throw FileStorageException("Couldn't parse filename of file")

        try {
            if (fileName.contains("..")) {
                throw FileStorageException("Filename contains invalid path sequence $fileName")
            }

            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)

            return fileName
        } catch (ex: IOException) {
            throw FileStorageException("Could not store file $fileName")
        }
    }

    fun loadFileAsResource(fileName: String) : Resource {
        try {
            val filePath = fileStorageLocation.resolve(fileName).normalize()
            val resource = UrlResource(filePath.toUri())
            if (resource.exists()) {
                return resource
            } else {
                throw WebFileNotFoundException("File not found $fileName")
            }
        } catch (ex: MalformedURLException) {
            throw WebFileNotFoundException("File not found $fileName")
        }
    }
}
