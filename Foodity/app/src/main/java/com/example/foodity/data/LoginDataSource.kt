package com.example.foodity.data

import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor() {

   /* fun login(username: String, password: String): Result<User> {
        try {
            val request = ServiceBuilder().buildService(UserService::class.java)
            val call = request.getUsersByUsername(username)
            val user = call.execute().body()
            if (user?.password == password) {
                Result.Success(user)
            }
            return Result.Error(Exception("Wrong credentials"))
            *//*call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (!response.isSuccessful) return
                    val user: User? = response.body()
                    if (user?.password == password) {
                        loggedInUser = user
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    return
                }
            })*//*
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }*/

    fun logout() {
        // TODO: revoke authentication
    }
}

