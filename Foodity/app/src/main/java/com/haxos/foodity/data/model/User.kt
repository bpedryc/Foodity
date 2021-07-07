package com.haxos.foodity.data.model

class User (
    var id: String,
    var username: String,
    var email: String,
    var password: String,
    var profile: Profile?,
    var roles: List<String>
) {
    fun redirectToEdit() {
        println("Redirect to profile")
    }

    fun ban() {
        println("Ban user")
    }
}