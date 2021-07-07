package com.haxos.foodity.ui.moderator

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.data.UserSession
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.IUserService
import com.haxos.foodity.ui.main.notes.content.RecyclerItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModeratorViewModel @Inject constructor(
        val userSession: UserSession,
        private val userService: IUserService
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<List<RecyclerItem>>(emptyList())
    val usersLiveData : LiveData<List<RecyclerItem>> = _usersLiveData

    fun fetchUsers() = viewModelScope.launch {
        val usersResponse = userService.getUsers()
        if (!usersResponse.isSuccessful) {
            val errorUser = User(-1, "ERROR", usersResponse.errorBody().toString(),
                    password = "", null, emptyList())
            _usersLiveData.value = listOf(errorUser.toRecyclerItem())
            return@launch
        }
        val users = usersResponse.body()
        _usersLiveData.value = users?.map { it.toRecyclerItem() }
    }

    fun logout(accountManagerActivity: Activity) {
        userSession.logout(accountManagerActivity)
    }

    private fun User.toRecyclerItem() : RecyclerItem {
        return RecyclerItem(
                data = this,
                variableId = BR.viewModel,
                layoutId = R.layout.recyclerview_user
        )
    }
}