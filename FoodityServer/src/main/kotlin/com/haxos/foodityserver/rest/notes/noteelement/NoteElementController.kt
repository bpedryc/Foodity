package com.haxos.foodityserver.rest.notes.noteelement

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/elements")
class NoteElementController(
    val elementService: NoteElementService
) {

    @GetMapping()
    fun findByNoteId(@RequestParam noteId: Long) =
        elementService.getElementsFromNote(noteId)

    /*@PutMapping()
    fun editNotes(@RequestBody request: NoteElementsRequest) =
        elementService.editElements(request.noteElementRequests)*/

}