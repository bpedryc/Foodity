package com.haxos.foodity.ui.main.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.AuthManager
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.retrofit.INotesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val notesService: INotesService
): ViewModel() {

    private val _searchLiveData = MutableLiveData<List<Note>>()
    val searchLiveData: LiveData<List<Note>> = _searchLiveData

    val searchListener = SearchListener()

    inner class SearchListener : SearchView.OnQueryTextListener{
        val cachedNotes = ArrayList<Note>()
        init {
            val profileId = authManager.profileId
            if (profileId != null) {
                loadNotes(profileId)
            }
        }

        private fun loadNotes(profileId: Long) {
            notesService.getNotesByProfile(profileId).enqueue(object : Callback<List<Note>> {
                override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        cachedNotes.addAll(responseBody)
                    }
                }
                override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

        override fun onQueryTextChange(newText: String): Boolean {
            if (newText.isEmpty()) {
                _searchLiveData.value = ArrayList()
                return true
            }
            _searchLiveData.value = cachedNotes.filter { it.name.contains(newText, true) }
            return true
        }
        override fun onQueryTextSubmit(query: String): Boolean {
            TODO("Not yet implemented")
        }
    }
}