package com.haxos.foodityserver.notes

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
}