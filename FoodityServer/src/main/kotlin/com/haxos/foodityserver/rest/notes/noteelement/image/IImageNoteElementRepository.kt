package com.haxos.foodityserver.rest.notes.noteelement.image

import com.haxos.foodityserver.rest.notes.noteelement.ImageNoteElement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IImageNoteElementRepository : JpaRepository<ImageNoteElement, Long> {

    @Query("SELECT e FROM ImageNoteElement e where e.note.id = ?1")
    fun findByNote(noteId: Long) : List<ImageNoteElement>

}
