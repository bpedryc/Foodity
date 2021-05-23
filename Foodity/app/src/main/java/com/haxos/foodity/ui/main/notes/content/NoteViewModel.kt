package com.haxos.foodity.ui.main.notes.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.model.GenericResult
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteRequest
import com.haxos.foodity.retrofit.INotesService
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val notesService: INotesService
) : ViewModel() {

    private val _noteLiveData = MutableLiveData<Note>()
    val noteLiveData : LiveData<Note> = _noteLiveData

    private val _noteEditResult = MutableLiveData<GenericResult>()
    val noteEditResult : LiveData<GenericResult> = _noteEditResult

    var noteId: Long? = null
    set(id) {
        field = id
        field?.let { fetchNote(it) }
    }

    private fun fetchNote(noteId: Long) = viewModelScope.launch {
        val response = notesService.getNoteById(noteId)
        _noteLiveData.value = response.body() ?: return@launch
    }

    fun editNote(noteName: String, noteDescription: String) = viewModelScope.launch {
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
        if (editedNote.body() != null) {
            _noteEditResult.value = GenericResult(success = 1)
        } else {
            _noteEditResult.value = GenericResult(error = 1)
        }
    }

    fun deleteNote() = viewModelScope.launch {
        noteId?.let {
            val response = notesService.delete(it)
            if (response.isSuccessful && response.body() == true) {
                _noteLiveData.value = null
            }
        }
    }

}