package com.haxos.foodity.ui.main.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.data.UserSession
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.retrofit.INotesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CategoriesGridViewModel @Inject constructor(
    private val userSession: UserSession,
    private val notesService: INotesService
) {

    private val _categoriesLiveData = MutableLiveData<List<NotesCategory>>()
    val categoriesLiveData: LiveData<List<NotesCategory>> = _categoriesLiveData


    init {
        notesService.getCategoriesByUsername(userSession.user!!.username).enqueue(object :
            Callback<List<NotesCategory>> {
            override fun onResponse(call: Call<List<NotesCategory>>, response: Response<List<NotesCategory>>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _categoriesLiveData.value = responseBody
                }
            }
            override fun onFailure(call: Call<List<NotesCategory>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}