package com.haxos.foodityserver.rest.profiles

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IFollowersRepository : JpaRepository<Follower, Long> {

    @Query("SELECT f FROM Follower f WHERE f.from.id = ?1 AND f.to.id = ?2")
    fun findFromTo(from : Long, to : Long): Optional<Follower>

}
