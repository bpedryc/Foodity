package com.haxos.foodity.ui.main.notes.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.data.model.*
import com.haxos.foodity.retrofit.INoteElementService
import com.haxos.foodity.retrofit.INotesService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val notesService: INotesService,
    private val elementsService: INoteElementService
) : ViewModel() {

    private val _noteLiveData = MutableLiveData<MutableList<RecyclerItem>>()
    val noteLiveData : LiveData<MutableList<RecyclerItem>> = _noteLiveData

    private val _noteEditResult = MutableLiveData<GenericResult>()
    val noteEditResult : LiveData<GenericResult> = _noteEditResult

    private val _imageElementRequest = MutableLiveData<GenericResult>()
    val imageElementRequest : LiveData<GenericResult> = _imageElementRequest

    private val _imageElementResponse = MutableLiveData<GenericResult>()
    val imageElementResponse : LiveData<GenericResult> = _imageElementResponse

    var editable: Boolean = false

    var noteId: Long? = null
    set(id) {
        field = id
        field?.let { fetchNote(it) }
    }

    private fun moveUpElement(elementViewModel: NoteElementViewModel) {
        val elements : MutableList<RecyclerItem> = _noteLiveData.value ?: return
        val index : Int = elements.indexOfFirst {
            it.data == elementViewModel
        }
        if (index <= 1) {
            return
        }

        val prevElement = elements[index - 1]
        elements[index - 1] = elements[index]
        elements[index] = prevElement
        _noteLiveData.value = _noteLiveData.value
    }

    private fun moveDownElement(elementViewModel: NoteElementViewModel) {
        val elements : MutableList<RecyclerItem> = _noteLiveData.value ?: return
        val index : Int = elements.indexOfFirst {
            it.data == elementViewModel
        }
        if (index == -1 || index >= elements.size - 1) {
            return
        }

        val nextElement = elements[index + 1]
        elements[index + 1] = elements[index]
        elements[index] = nextElement
        _noteLiveData.value = _noteLiveData.value
    }

    private fun deleteElement(elementViewModel: NoteElementViewModel) {
        _noteLiveData.value?.removeIf { it.data == elementViewModel }
        _noteLiveData.value = _noteLiveData.value
    }

    private fun fetchNote(noteId: Long) = viewModelScope.launch {
        val noteResponse = async {notesService.getNoteById(noteId) }
        val elementsResponse = async { elementsService.getByNoteId(noteId) }

        val note : Note = noteResponse.await().body() ?: return@launch
        val elements = elementsResponse.await().body() ?: return@launch
        note.elements = elements

        val recyclerItems = ArrayList<RecyclerItem>()
        recyclerItems.add(note.toRecyclerItem())
        recyclerItems.addAll(note.elements
                .sortedBy { it.order }
                .map { toViewModel(it) }
                .map { it.toRecyclerItem(editable) })

        _noteLiveData.value = recyclerItems
    }


    private fun toViewModel(noteElement: NoteElement) : NoteElementViewModel {
        val elementActionListener = ElementActionListener(
                onMoveUp = ::moveUpElement,
                onMoveDown = ::moveDownElement,
                onDelete = ::deleteElement
        )

        return when (noteElement) {
            is TextNoteElement -> TextNoteElementViewModel(noteElement, elementActionListener)
            is ListNoteElement -> ListNoteElementViewModel(noteElement, _noteLiveData, editable, elementActionListener)
            is ImageNoteElement ->  ImageNoteElementViewModel(noteElement, elementActionListener,
                    onEditImage = { _imageElementRequest.value = GenericResult(1) })
            else -> TODO()
        }
    }

    private fun Note.toRecyclerItem() : RecyclerItem {
        var layout = R.layout.recyclerview_note_header
        if (editable) {
            layout = R.layout.recyclerview_note_header_edit
        }
        return RecyclerItem(
                data = this,
                variableId = BR.note,
                layoutId = layout
        )
    }

    fun editNote() = viewModelScope.launch {
        val note : Note = (_noteLiveData.value?.get(0)?.data as Note?) ?: return@launch
        val request = NoteRequest(
                id = note.id,
                name = note.name,
                description = note.description,
                categoryId = note.categoryId,
                thumbnail = note.thumbnail)
        val editedNote = notesService.edit(request)
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