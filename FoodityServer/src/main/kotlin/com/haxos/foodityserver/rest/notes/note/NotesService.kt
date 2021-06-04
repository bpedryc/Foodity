package com.haxos.foodityserver.rest.notes.note

import com.haxos.foodityserver.rest.notes.category.INotesCategoriesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotesService (
    val notesRepository: INotesRepository,
    val categoriesRepository: INotesCategoriesRepository
){
    fun getNotesFromCategory(categoryId: Long) : List<Note> =
        notesRepository.findByCategory(categoryId)

    fun getNote(id: Long): Optional<Note> =
        notesRepository.findById(id)

    fun getNotesFromProfile(profileId: Long) : List<Note> =
        notesRepository.findByProfile(profileId)

    fun createNote(request: NoteRequest) : Note {
        val category = categoriesRepository.getOne(request.categoryId)
        val note = Note(request.name, request.description, request.thumbnail, category)
        return notesRepository.save(note)
    }

    fun deleteNote(noteId: Long) =
        notesRepository.deleteById(noteId)

    fun editNote(request: NoteRequest): Note {
        val note : Note = notesRepository.getOne(request.id!!)
        note.name = request.name
        note.description = request.description
        return notesRepository.save(note)
    }
}