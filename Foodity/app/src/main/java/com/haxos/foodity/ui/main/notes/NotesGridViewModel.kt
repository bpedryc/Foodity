package com.haxos.foodity.ui.main.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.data.AuthManager
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.retrofit.INotesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotesGridViewModel @Inject constructor(
    val authManager: AuthManager,
    val notesService: INotesService
) {
    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    fun fetchNotes(categoryId: Long) {
        notesService.getNotesByCategory(categoryId).enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _notesLiveData.value = responseBody
                }
            }
            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
