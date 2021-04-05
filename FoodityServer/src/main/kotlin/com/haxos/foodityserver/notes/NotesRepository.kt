package com.haxos.foodityserver.notes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface NotesRepository : JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.category.id = ?1")
    fun findByCategory(categoryId: Long): List<Note>

    @Query("SELECT n FROM Note n WHERE n.category.profile.id = ?1")
    fun findByProfile(profileId: Long): List<Note>
}