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

    @GetMapping(params = ["profileId"])
    fun findByProfileId(@RequestParam profileId: Long) : List<NotesCategory> =
        categoriesService.getCategoriesOfProfile(profileId)

    @PostMapping()
    fun createCategory(@RequestBody category: NotesCategoryRequest) =
        categoriesService.createCategory(category)

    @PutMapping()
    fun editCategory(@RequestBody category: NotesCategoryRequest) =
        categoriesService.editCategory(category)

    @DeleteMapping()
    fun deleteCategory(@RequestParam id: Long) : Boolean {
        categoriesService.deleteCategory(id)
        return true
    }

    @GetMapping("/{id}/profileId")
    fun getOwnerProfileId(@PathVariable("id") categoryId: Long) : Long {
        return categoriesService.getProfileId(categoryId)
    }

}