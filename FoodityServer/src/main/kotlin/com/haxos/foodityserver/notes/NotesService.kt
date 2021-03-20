package com.haxos.foodityserver.notes

import org.springframework.stereotype.Service

@Service
class NotesService (
    val notesRepository: NotesRepository
){
    fun getNotesFromCategory(categoryId: Long) : List<Note> =
        notesRepository.findByCategory(categoryId)
}