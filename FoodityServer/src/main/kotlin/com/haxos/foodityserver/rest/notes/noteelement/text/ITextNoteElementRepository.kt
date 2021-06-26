package com.haxos.foodityserver.rest.notes.noteelement.text

import com.haxos.foodityserver.rest.notes.noteelement.TextNoteElement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ITextNoteElementRepository : JpaRepository<TextNoteElement, Long> {

    @Query("SELECT e FROM TextNoteElement e where e.note.id = ?1")
    fun findByNote(noteId: Long) : List<TextNoteElement>

    /*@Query("SELECT n FROM Note n WHERE n.category.id = ?1")
    fun findByCategory(categoryId: Long): List<Note>

    @Query("SELECT n FROM Note n WHERE n.category.profile.id = ?1")
    fun findByProfile(profileId: Long): List<Note>*/


}
