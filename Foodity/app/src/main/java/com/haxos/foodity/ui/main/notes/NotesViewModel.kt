package com.haxos.foodity.ui.main.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.retrofit.NotesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotesViewModel @Inject constructor(
        private val loginRepository: LoginRepository,
        private val notesService: NotesService
): ViewModel() {

    private val _searchLiveData = MutableLiveData<List<Note>>()
    val searchLiveData: LiveData<List<Note>> = _searchLiveData

    private lateinit var cachedCategories: List<NotesCategory>

    val searchListener = SearchListener()

    init {
        notesService.getCategoriesByUsername(loginRepository.user!!.username).enqueue(object :
            Callback<List<NotesCategory>> {
            override fun onResponse(call: Call<List<NotesCategory>>, response: Response<List<NotesCategory>>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    cachedCategories = ArrayList(responseBody)
                }
            }
            override fun onFailure(call: Call<List<NotesCategory>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    inner class SearchListener : SearchView.OnQueryTextListener{
        override fun onQueryTextChange(newText: String): Boolean {
            if (newText.isEmpty()) {
                _searchLiveData.value = ArrayList()
                return true
            }

            val allNotes =  ArrayList<Note>()
            cachedCategories.forEach {
                allNotes.addAll(it.notes)
            }
            _searchLiveData.value = allNotes.filter { it.name.contains(newText, true) }
            return true
        }
        override fun onQueryTextSubmit(query: String): Boolean {
            TODO("Not yet implemented")
        }
    }
}