package com.haxos.foodityserver.rest.logs.profile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface IProfileLogsRepository : JpaRepository<ProfileLog, Long> {

    @Query("SELECT p FROM ProfileLog p WHERE p.profile.id = ?1")
    fun getByProfileId(followingProfileId: Long): List<ProfileLog>

}
