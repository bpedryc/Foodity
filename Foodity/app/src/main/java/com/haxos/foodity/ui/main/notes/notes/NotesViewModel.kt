package com.haxos.foodity.ui.main.notes.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteRequest
import com.haxos.foodity.retrofit.INotesCategoriesService
import com.haxos.foodity.retrofit.INotesService
import com.haxos.foodity.ui.main.notes.notesearch.NoteSearchListener
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val currentUserInfo: ICurrentUserInfo,
    private val notesService: INotesService,
) : ViewModel(), INoteSearchingViewModel {

    private val _notesLiveData = MutableLiveData<MutableList<Note>>()
    val notesLiveData: LiveData<MutableList<Note>> = _notesLiveData

    private val _searchLiveData = MutableLiveData<List<Note>>()
    override val searchLiveData: LiveData<List<Note>> = _searchLiveData

    private var categoryId: Long? = null

    fun fetchNotes(categoryId: Long) {
        this.categoryId = categoryId

        viewModelScope.launch {
            val notesResponse = notesService.getNotesByCategory(categoryId)
            val notes = notesResponse.body() ?: return@launch
            _notesLiveData.value = notes.toMutableList()
        }
    }

    fun createNote(name: String) {
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

    fun duplicateNote(selectedNoteId: Long) = viewModelScope.launch {
        val response = notesService.duplicate(selectedNoteId)
        val duplicate = response.body() ?: return@launch
        _notesLiveData.value?.add(duplicate)
        _notesLiveData.value = _notesLiveData.value
    }

    fun isCurrentProfile(profileId: Long): Boolean {
        return currentUserInfo.profileId == profileId
    }

    override val searchListener: SearchView.OnQueryTextListener
        get() = NoteSearchListener(currentUserInfo, notesService, _searchLiveData)
}
