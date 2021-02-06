package com.haxos.foodity

import com.haxos.foodity.data.model.User

interface LoginCallback {
    fun onSuccess(user: User)
    fun onError(error: Int)
}