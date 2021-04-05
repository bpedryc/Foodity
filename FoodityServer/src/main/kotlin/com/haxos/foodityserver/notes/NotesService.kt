package com.haxos.foodityserver.notes

import org.springframework.stereotype.Service
import java.util.*

@Service
class NotesService (
    val notesRepository: NotesRepository
){
    fun getNotesFromCategory(categoryId: Long) : List<Note> =
        notesRepository.findByCategory(categoryId)

    fun getNote(id: Long): Optional<Note> =
        notesRepository.findById(id)

    fun getNotesFromProfile(profileId: Long) : List<Note> =
        notesRepository.findByProfile(profileId)
}