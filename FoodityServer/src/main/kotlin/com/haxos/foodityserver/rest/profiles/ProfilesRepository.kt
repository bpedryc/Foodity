package com.haxos.foodityserver.rest.profiles

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProfilesRepository : JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.username = ?1")
    fun findByUsername(username : String): Profile

    /*@Query("SELECT u FROM User u WHERE u.email = ?1")
    fun findByEmail(email : String): Profile*/
}