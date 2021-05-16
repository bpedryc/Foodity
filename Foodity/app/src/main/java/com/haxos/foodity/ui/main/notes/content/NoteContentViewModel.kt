package com.haxos.foodity.ui.main.notes.content

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteRequest
import com.haxos.foodity.retrofit.INotesService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NoteContentViewModel @Inject constructor(
    private val notesService: INotesService
) : ViewModel() {

    val noteLiveData = MutableLiveData<Note>()
    private val _noteLiveData = noteLiveData

    var noteId: Long? = null
    set(id) {
        field = id
        field?.let { fetchNote(it) }
    }

    private fun fetchNote(noteId: Long) {
        notesService.getNoteById(noteId).enqueue(object : Callback<Note> {
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                _noteLiveData.value = response.body()
            }
            override fun onFailure(call: Call<Note>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun deleteNote() = viewModelScope.launch {
        noteId?.let {
            val response = notesService.delete(it)
            if (response.isSuccessful && response.body() == true) {
                _noteLiveData.value = null
            }
        }
    }

    fun noteEdited(noteName: String, noteDescription: String) = viewModelScope.launch {
        val oldNote : Note = _noteLiveData.value ?: return@launch
        val note = NoteRequest(
            id = oldNote.id,
            name = noteName,
            description = noteDescription,
            categoryId = oldNote.categoryId,
            thumbnail = oldNote.thumbnail,
            elements = oldNote.elements
        )
        val editedNote = notesService.edit(note)
        _noteLiveData.value = editedNote.body() ?: return@launch
    }


}