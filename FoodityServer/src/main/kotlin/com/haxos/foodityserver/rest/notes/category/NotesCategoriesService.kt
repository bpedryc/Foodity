package com.haxos.foodityserver.rest.notes.category

import com.haxos.foodityserver.rest.profiles.IProfilesRepository
import javassist.NotFoundException
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

    fun getCategoriesOfProfile(profileId: Long): List<NotesCategory> {
        val profile = profilesRepository.findById(profileId).
                orElseThrow { NotFoundException("There is no profile with id $profileId") }
        val categories : List<NotesCategory> = profile.notesCategories
        return categories
    }

    fun deleteCategory(id: Long) {
        categoriesRepository.deleteById(id)
    }

    fun getProfileId(categoryId: Long): Long {
        val category = categoriesRepository.findById(categoryId)
            .orElseThrow { NotFoundException("No category with id $categoryId found") }
        val ownerProfile = category.profile
        return ownerProfile.getId()!!
    }

}