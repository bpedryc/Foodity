package com.haxos.foodity.data.model

interface IUserActionListener {
    fun onEdit(profile: Profile)
    fun onPageRedirect(profile: Profile)
}