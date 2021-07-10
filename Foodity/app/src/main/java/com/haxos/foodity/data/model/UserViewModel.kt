package com.haxos.foodity.data.model

class UserViewModel (
        val user: User,
        val profile: Profile,
        val listener: IUserActionListener
) {

    fun redirectToEdit() {
        listener.onPageRedirect(profile)
    }

    fun ban() {
        profile.blocked = !profile.blocked
        listener.onEdit(profile)
    }
}