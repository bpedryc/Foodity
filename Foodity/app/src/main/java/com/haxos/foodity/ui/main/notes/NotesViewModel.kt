package com.haxos.foodity.ui.main.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.retrofit.INotesService
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val currentUserInfo: ICurrentUserInfo,
    private val notesService: INotesService
): ViewModel(), ISearchingViewModel {

    private val _searchLiveData = MutableLiveData<List<Note>>()
    val searchLiveData: LiveData<List<Note>> = _searchLiveData

    private var cachedNotes: List<Note>? = null
    private suspend fun cacheNotes() {
        val profileId: Long = currentUserInfo.profileId!!
        notesService.getNotesByProfile(profileId)
    }

    override suspend fun resetNoteSearch() {
        _searchLiveData.value = ArrayList()
    }

    override suspend fun searchNotes(searchText: String) {
        if (cachedNotes == null) {
            cacheNotes()
        }
        _searchLiveData.value = cachedNotes?.filter { it.name.contains(searchText, ignoreCase = true) }
    }
}

interface ISearchingViewModel {
    suspend fun resetNoteSearch()
    suspend fun searchNotes(searchText: String)
}
