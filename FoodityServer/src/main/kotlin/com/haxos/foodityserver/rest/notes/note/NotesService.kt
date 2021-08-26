package com.haxos.foodityserver.rest.notes.note

import com.haxos.foodityserver.rest.logs.note.INoteLogsRepository
import com.haxos.foodityserver.rest.logs.note.NoteLog
import com.haxos.foodityserver.rest.logs.note.NoteLogsService
import com.haxos.foodityserver.rest.notes.category.INotesCategoriesRepository
import com.haxos.foodityserver.rest.notes.noteelement.NoteElementService
import javassist.NotFoundException
import org.hibernate.annotations.NotFound
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class NotesService (
    val notesRepository: INotesRepository,
    val categoriesRepository: INotesCategoriesRepository,
    val noteLogsRepository: INoteLogsRepository,
    val noteElementService: NoteElementService
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
        val createdNote = notesRepository.save(note)

        val profile = category.profile
        val noteLog = NoteLog(NoteLog.Type.CREATE, profile, note, LocalDateTime.now())
        noteLogsRepository.save(noteLog)

        return createdNote
    }

    fun editNote(request: NoteRequest): Note {
        val noteId = request.id
            ?: throw NotFoundException("Can't edit a note without id")

        val note : Note = notesRepository.getOne(noteId)
        val noteOwner = note.category?.profile
            ?: throw NotFoundException("Note with $noteId has no owner")

        note.name = request.name
        note.description = request.description
        val editedNote = notesRepository.save(note)

        val noteLog = NoteLog(NoteLog.Type.EDIT, noteOwner, note, LocalDateTime.now())
        noteLogsRepository.save(noteLog)

        return editedNote
    }

    fun deleteNote(noteId: Long) {
        var note = notesRepository.findById(noteId)
            .orElseThrow { NotFoundException("Can't find a note with id $noteId to delete") }
        val noteOwner = note.category?.profile
            ?: throw NotFoundException("Note with $noteId has no owner")

        note.category = null
        note = notesRepository.save(note)

        val noteLog = NoteLog(NoteLog.Type.DELETE, noteOwner, note, LocalDateTime.now())
        noteLogsRepository.save(noteLog)
    }
}