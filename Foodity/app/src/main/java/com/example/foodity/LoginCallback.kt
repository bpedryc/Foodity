package com.example.foodity

import com.example.foodity.data.model.User

interface LoginCallback {
    fun onSuccess(user: User)
    fun onError(error: Int)
}