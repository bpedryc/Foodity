package com.haxos.foodityserver.rest.notes.noteelement.text

import com.haxos.foodityserver.rest.notes.note.INotesRepository
import com.haxos.foodityserver.rest.notes.noteelement.TextNoteElement
import javassist.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/text-elements")
class TextElementController (
    val elementRepository: ITextNoteElementRepository,
    val notesRepository: INotesRepository
){
    @PostMapping
    fun create(@RequestBody request: TextNoteElementRequest) : TextNoteElement {
        val parentNoteId : Long = request.noteId ?: throw Exception("Cannot add note element without a parent note")
        val parentNote = notesRepository.findById(parentNoteId)
            .orElseThrow { NotFoundException("Cannot add an element to non-existent note") }
        val element = TextNoteElement(request.title, request.orderNumber, parentNote, request.contents)

        return elementRepository.save(element)
    }

    @PutMapping
    fun edit(@RequestBody request: TextNoteElementRequest): TextNoteElement {
        val requestId : Long = request.id ?: throw Exception("Cannot edit TextNoteElement with id = null")
        val element = elementRepository.findById(requestId)
            .orElseThrow { NotFoundException("TextNoteElement with id $requestId not found") }

        element.title = request.title
        element.orderNumber = request.orderNumber
        element.contents = request.contents

        return elementRepository.save(element)
    }
}