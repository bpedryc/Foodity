package com.haxos.foodityserver.rest.notes.category

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class NotesCategoriesController (
    val categoriesService: NotesCategoriesService
) {
    @GetMapping
    fun all() : List<NotesCategory> =
        categoriesService.getAllCategories()

    @GetMapping(params = ["username"])
    fun findByUserId(@RequestParam username: String) : List<NotesCategory> =
        categoriesService.getCategoriesOfUser(username)

    @PostMapping()
    fun createCategory(@RequestBody category: NotesCategoryRequest) =
        categoriesService.createCategory(category)

}