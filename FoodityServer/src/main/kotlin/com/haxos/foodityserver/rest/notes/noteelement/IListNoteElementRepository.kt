package com.haxos.foodityserver.rest.notes.noteelement

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IListNoteElementRepository : JpaRepository<ListNoteElement, Long> {

    @Query("SELECT e FROM ListNoteElement e where e.note.id = ?1")
    fun findByNote(noteId: Long) : List<ListNoteElement>

}
