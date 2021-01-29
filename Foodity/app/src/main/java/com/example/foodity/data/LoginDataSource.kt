package com.example.foodity.data

import com.example.foodity.ServiceBuilder
import com.example.foodity.UserAPI
import com.example.foodity.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource @Inject constructor() {

    fun login(username: String, password: String): Result<User> {
        try {
            val request = ServiceBuilder().buildService(UserAPI::class.java)
            val call = request.getUsers()
            println("REST CALL:")
            call.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        println(response.body())
                    }
                }
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    println(t.message)
                }
            })

            val fakeUser = User(java.util.UUID.randomUUID().toString(), "Jane Doe", "jdoe@gmail.com", "haslo123")
            return Result.Success(fakeUser)
            // TODO: handle loggedInUser authentication

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

