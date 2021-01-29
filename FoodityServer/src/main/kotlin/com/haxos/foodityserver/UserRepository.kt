package com.haxos.foodityserver

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    fun findByEmail(email : String): User

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    fun findByUsername(username : String): User
}