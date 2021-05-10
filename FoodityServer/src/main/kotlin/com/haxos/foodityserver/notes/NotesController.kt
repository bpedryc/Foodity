package com.haxos.foodityserver.notes

import org.springframework.web.bind.annotation.*
import java.util.*

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
}