package com.haxos.foodityserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService (
    @Autowired val userRepository: UserRepository
) {
    fun list() : List<User> {
        return userRepository.findAll()
    }
}