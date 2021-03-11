package com.haxos.foodity.ui.main.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.retrofit.NotesService
import com.haxos.foodity.ui.main.social.SocialViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotesViewModel @Inject constructor(
        private val notesService: NotesService
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Notes Fragment"
    }
    val text: LiveData<String> = _text

    private val _searchLiveData = MutableLiveData<List<Note>>()
    val searchLiveData: LiveData<List<Note>> = _searchLiveData

    private val cachedNotes = ArrayList<Note>()

    val searchListener = SearchListener()

    init {
        notesService.getAll().enqueue(object : Callback<List<Note>> {
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

    inner class SearchListener : SearchView.OnQueryTextListener{
        override fun onQueryTextChange(newText: String): Boolean {
            _searchLiveData.value = cachedNotes.filter { it.name.contains(newText) }
            return true
        }
        override fun onQueryTextSubmit(query: String): Boolean {
            TODO("Not yet implemented")
        }
    }
}