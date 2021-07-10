package com.haxos.foodity.data.model

class UserViewModel (
        val user: User,
        val profile: Profile,
        val listener: IUserActionListener
) {

    fun redirectToEdit() {
        print("REDIRECT EDIT")
    }

    fun ban() {
        profile.blocked = !profile.blocked
        listener.onEdit(profile)
    }
}