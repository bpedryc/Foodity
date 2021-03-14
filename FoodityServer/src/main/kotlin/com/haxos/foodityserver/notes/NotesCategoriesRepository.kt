package com.haxos.foodityserver.notes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotesCategoriesRepository : JpaRepository<NotesCategory, Long> {

   /* @Query("SELECT c FROM NotesCategory c WHERE c.profile_id = ?1")
    fun findByUser(userId : Long): List<NotesCategory>*/
}

