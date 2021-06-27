package com.haxos.foodityserver.rest.notes.noteelement.list

import com.haxos.foodityserver.rest.notes.note.INotesRepository
import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElement
import javassist.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/list-elements")
class ListElementController (
    val elementRepository: IListNoteElementRepository,
    val notesRepository: INotesRepository
){
    @PostMapping
    fun create(@RequestBody request: ListNoteElementRequest) : ListNoteElement {
        val parentNoteId : Long = request.noteId ?: throw Exception("Cannot add note element without a parent note")
        val parentNote = notesRepository.findById(parentNoteId)
            .orElseThrow { NotFoundException("Cannot add an element to non-existent note") }
        val element = ListNoteElement(request.title, request.orderNumber, parentNote, request.entries.toMutableList())

        return elementRepository.save(element)
    }

    @PutMapping
    fun edit(@RequestBody request: ListNoteElementRequest) : ListNoteElement {
        val requestId : Long = request.id ?: throw Exception("Cannot edit ListNoteElement with id = null")
        val element = elementRepository.findById(requestId)
            .orElseThrow { NotFoundException("ListNoteElement with id $requestId not found") }

        element.title = request.title
        element.orderNumber = request.orderNumber

        element.entries.clear()
        element.entries.addAll(request.entries)

        return elementRepository.save(element)
    }
}