package com.haxos.foodity.ui.main.notes.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteRequest
import com.haxos.foodity.retrofit.INotesService
import com.haxos.foodity.ui.main.notes.notesearch.NoteSearchListener
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotesGridViewModel @Inject constructor(
    private val currentUserInfo: ICurrentUserInfo,
    private val notesService: INotesService
) : ViewModel(), INoteSearchingViewModel {

    private val _notesLiveData = MutableLiveData<MutableList<Note>>()
    val notesLiveData: LiveData<MutableList<Note>> = _notesLiveData

    private val _searchLiveData = MutableLiveData<List<Note>>()
    override val searchLiveData: LiveData<List<Note>> = _searchLiveData

    private var categoryId: Long? = null

    fun fetchNotes(categoryId: Long) {
        this.categoryId = categoryId
        notesService.getNotesByCategory(categoryId).enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    _notesLiveData.value = responseBody.toMutableList()
                }
            }
            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun addNote(name: String) {
        val categoryId : Long = categoryId ?: return
        val noteRequest = NoteRequest(
            name = name, thumbnail = 0, description = "", categoryId = categoryId)
        viewModelScope.launch {
            val addedNote = notesService.add(noteRequest).body()
            if (addedNote != null) {
                _notesLiveData.value?.add(addedNote)
                _notesLiveData.value = _notesLiveData.value
            }
        }
    }

    override val searchListener: SearchView.OnQueryTextListener
        get() = NoteSearchListener(currentUserInfo, notesService, _searchLiveData)
}
