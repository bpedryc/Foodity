package com.haxos.foodity.ui.main.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.retrofit.INotesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NoteContentViewModel @Inject constructor(
    private val notesService: INotesService
) : ViewModel() {

    val noteLiveData = MutableLiveData<Note>()
    private val _noteLiveData = noteLiveData

    fun fetchNote(noteId: Long) {
        notesService.getNoteById(noteId).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                _noteLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}