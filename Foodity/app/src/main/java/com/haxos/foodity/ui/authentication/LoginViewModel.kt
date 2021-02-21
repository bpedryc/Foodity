package com.haxos.foodity.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.haxos.foodity.LoginCallback

import com.haxos.foodity.R
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.data.model.User
import javax.inject.Inject

class LoginViewModel @Inject constructor (
    private val loginRepository: LoginRepository
) : ViewModel()
{
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private var _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        loginRepository.login(username, password, object : LoginCallback {
            override fun onSuccess(user: User) {
                _loginResult.value =
                        LoginResult(success = LoggedInUserView(displayName = user.username))
            }
            override fun onError(error: Int) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }

        })
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
