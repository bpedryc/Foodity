package com.haxos.foodityserver.rest.notes.category

import com.haxos.foodityserver.rest.profiles.ProfilesRepository
import org.springframework.stereotype.Service

@Service
class NotesCategoriesService (
    val notesRepository: NotesCategoriesRepository,
    val profilesRepository: ProfilesRepository
){
    fun getAllCategories() : List<NotesCategory> {
        return notesRepository.findAll()
    }

    fun getCategoriesOfUser(username: String): List<NotesCategory> {
        return profilesRepository.findByUsername(username).notesCategories
    }
}