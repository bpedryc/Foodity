package com.haxos.foodityserver.notes

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class NotesCategoriesController (
    val notesService: NotesCategoriesService
) {
    @GetMapping
    fun all() : List<NotesCategory> =
        notesService.getAllCategories()

    @GetMapping(params = ["username"])
    fun findByUserId(@RequestParam username: String) : List<NotesCategory> =
        notesService.getCategoriesOfUser(username)
}