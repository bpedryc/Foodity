package com.haxos.foodityserver.rest.notes.noteelement.image

import com.haxos.foodityserver.rest.notes.noteelement.ImageNoteElement
import javassist.NotFoundException
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/image-elements")
class ImageElementController (
    val repository: IImageNoteElementRepository
){
    @PutMapping
    fun edit(@RequestBody request: ImageNoteElementRequest) : ImageNoteElement {
        val requestId : Long = request.id
        val element = repository.findById(requestId)
            .orElseThrow { NotFoundException("ImageNoteElement with id $requestId not found") }

        element.title = request.title
        element.orderNumber = request.order
        element.sourcePath = request.sourcePath

        return repository.save(element)
    }
}