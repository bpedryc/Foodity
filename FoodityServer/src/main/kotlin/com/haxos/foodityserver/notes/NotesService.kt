package com.haxos.foodityserver.notes

import org.springframework.stereotype.Service
import java.util.*

@Service
class NotesService (
    val notesRepository: NotesRepository,
    val categoriesRepository: NotesCategoriesRepository
){
    fun getNotesFromCategory(categoryId: Long) : List<Note> =
        notesRepository.findByCategory(categoryId)

    fun getNote(id: Long): Optional<Note> =
        notesRepository.findById(id)

    fun getNotesFromProfile(profileId: Long) : List<Note> =
        notesRepository.findByProfile(profileId)

    fun createNote(request: NoteRequest) : Note {
        val category = categoriesRepository.getOne(request.categoryId)
        val note = Note(request.name, request.description, request.thumbnail, category, request.elements)
        return notesRepository.save(note)
    }
}