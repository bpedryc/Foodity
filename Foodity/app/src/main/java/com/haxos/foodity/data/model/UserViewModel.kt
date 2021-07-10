package com.haxos.foodity.data.model

import com.haxos.foodity.R

class UserViewModel (
        val user: User,
        val profile: Profile,
        val listener: IUserActionListener
) {
    val blockButtonColor : Int = if (profile.blocked) {
        R.color.colorPrimary//R.color.design_default_color_error
    } else {
        R.color.white
    }

    fun redirectToEdit() {
        print("REDIRECT EDIT")
    }

    fun ban() {
        profile.blocked = !profile.blocked
        listener.onEdit(profile)
    }
}