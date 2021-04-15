package com.haxos.foodityserver.logs

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface INoteLogsRepository : JpaRepository<NoteLog, Long> {

    @Query("SELECT p FROM ProfileLog p WHERE p.profile.id = ?1")
    fun getByProfileId(id: Long): List<NoteLog>
}
