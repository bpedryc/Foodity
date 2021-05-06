package com.haxos.foodity.ui.main.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.retrofit.INotesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteSearchListener(
    currentUserInfo: ICurrentUserInfo,
    private val notesService: INotesService,
    private val searchLiveData: MutableLiveData<List<Note>>
): SearchView.OnQueryTextListener {

    val cachedNotes = ArrayList<Note>()
    init {
        val profileId : Long? = currentUserInfo.user?.profile?.id
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
            searchLiveData.value = ArrayList()
            return true
        }
        searchLiveData.value = cachedNotes.filter { it.name.contains(newText, true) }
        return true
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        TODO("Not yet implemented")
    }
}