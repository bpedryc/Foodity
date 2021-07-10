package com.haxos.foodity.ui.authentication.login

import android.app.Activity
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.R
import com.haxos.foodity.data.UserSession
import com.haxos.foodity.ui.authentication.login.model.LoggedInUserView
import com.haxos.foodity.ui.authentication.login.model.LoginFormState
import com.haxos.foodity.ui.authentication.login.model.LoginResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor (
    private val userSession: UserSession
) : ViewModel()
{
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private var _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) = viewModelScope.launch {
        userSession.login(username, password)
        if (userSession.isBlocked) {
            _loginResult.value = LoginResult(error = R.string.login_failed_blocked)
            return@launch
        }
        if (userSession.isLoggedIn) {
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = username))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
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

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun logout(accountManagerActivity: Activity) {
        userSession.logout(accountManagerActivity)
    }
}
