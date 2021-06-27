package com.haxos.foodityserver.rest.notes.noteelement.image

import com.haxos.foodityserver.rest.notes.note.INotesRepository
import com.haxos.foodityserver.rest.notes.noteelement.ImageNoteElement
import javassist.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/image-elements")
class ImageElementController (
    val elementRepository: IImageNoteElementRepository,
    val notesRepository: INotesRepository
){
    @PostMapping
    fun create(@RequestBody request: ImageNoteElementRequest) : ImageNoteElement {
        val parentNoteId : Long = request.noteId ?: throw Exception("Cannot add note element without a parent note")
        val parentNote = notesRepository.findById(parentNoteId)
            .orElseThrow { NotFoundException("Cannot add an element to non-existent note") }
        val element = ImageNoteElement(request.title, request.orderNumber, parentNote, request.sourcePath)

        return elementRepository.save(element)
    }

    @PutMapping
    fun edit(@RequestBody request: ImageNoteElementRequest) : ImageNoteElement {
        val requestId : Long = request.id ?: throw Exception("Cannot edit ImageNoteElement with id = null")
        val element = elementRepository.findById(requestId)
            .orElseThrow { NotFoundException("ImageNoteElement with id $requestId not found") }

        element.title = request.title
        element.orderNumber = request.orderNumber
        element.sourcePath = request.sourcePath

        return elementRepository.save(element)
    }

    @DeleteMapping(params = ["id"])
    fun delete(@RequestParam id: Long) : Boolean {
        val element = elementRepository.findById(id)
        var elementDeleted = false
        element.ifPresent {
            elementRepository.delete(it)
            elementDeleted = true
        }
        return elementDeleted
    }
}