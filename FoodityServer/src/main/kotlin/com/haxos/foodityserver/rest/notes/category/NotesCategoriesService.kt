package com.haxos.foodityserver.rest.notes.category

import com.haxos.foodityserver.rest.profiles.IProfilesRepository
import org.springframework.stereotype.Service

@Service
class NotesCategoriesService (
    val categoriesRepository: INotesCategoriesRepository,
    val profilesRepository: IProfilesRepository,
){
    fun getAllCategories() : List<NotesCategory> {
        return categoriesRepository.findAll()
    }

    fun createCategory(request: NotesCategoryRequest) : NotesCategory {
        val profile = profilesRepository.getOne(request.profileId)
        val category = NotesCategory(name = request.name, thumbnail = request.thumbnail, profile = profile)
        return categoriesRepository.save(category)
    }

    fun getCategoriesOfUser(username: String): List<NotesCategory> {
        return profilesRepository.findByUsername(username).notesCategories
    }

}