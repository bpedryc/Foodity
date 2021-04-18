package com.haxos.foodityserver.logs

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface INoteLogsRepository : JpaRepository<NoteLog, Long> {

    @Query("SELECT n FROM NoteLog n WHERE n.profile.id = ?1")
    fun getByProfileId(id: Long): List<NoteLog>
}
