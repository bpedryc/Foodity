package com.haxos.foodityserver.notes

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class NotesCategoriesController (
    val categoriesRepository: NotesCategoriesRepository
) {
    @GetMapping
    fun all() : List<NotesCategory> =
        categoriesRepository.findAll()

    /*@GetMapping
    fun findByUserId(@RequestParam("userId") userId: Long) : List<NotesCategory> =
        categoriesRepository.findByUser(userId)*/
}