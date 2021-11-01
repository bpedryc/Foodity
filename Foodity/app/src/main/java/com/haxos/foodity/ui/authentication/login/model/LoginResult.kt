package com.haxos.foodity.ui.authentication.login.model

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
