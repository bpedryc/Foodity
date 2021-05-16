package com.haxos.foodityserver.rest.notes.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface INotesCategoriesRepository : JpaRepository<NotesCategory, Long> {

   /* @Query("SELECT c FROM NotesCategory c WHERE c.profile_id = ?1")
    fun findByUser(userId : Long): List<NotesCategory>*/
}
