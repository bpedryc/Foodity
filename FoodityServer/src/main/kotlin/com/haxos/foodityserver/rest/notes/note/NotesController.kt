package com.haxos.foodityserver.rest.notes.note

import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import java.util.*
import javax.ws.rs.BadRequestException

@RestController
@RequestMapping("/notes")
class NotesController (
    val notesService: NotesService
) {
    @GetMapping(params = ["id"])
    fun findById(@RequestParam id: Long) : Optional<Note> =
        notesService.getNote(id)

    @GetMapping(params = ["categoryId"])
    fun findByCategoryId(@RequestParam categoryId: Long) : List<Note> =
       notesService.getNotesFromCategory(categoryId)

    @GetMapping(params = ["profileId"])
    fun findByProfileId(@RequestParam profileId: Long) : List<Note> =
        notesService.getNotesFromProfile(profileId)

    @PostMapping()
    fun createNote(@RequestBody request: NoteRequest) : Note =
        notesService.createNote(request)

    @PutMapping()
    fun editNote(@RequestBody request: NoteRequest) : Note {
        if (request.id == null) {
            throw BadRequestException()
        }
        return notesService.editNote(request)
    }

    @DeleteMapping(params = ["id"])
    fun deleteNote(@RequestParam id: Long) : Boolean {
        notesService.deleteNote(id)
        return true
    }
}