package com.haxos.foodity.ui.main.notes.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.*
import com.haxos.foodity.retrofit.INotesService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

class NoteViewModel @Inject constructor(
        private val currentUser: ICurrentUserInfo,
        private val notesService: INotesService,
        private val elementsRepository: NoteElementsRepository,
        private val fileService: IFileService
) : ViewModel() {

    private val _noteLiveData = MutableLiveData<MutableList<RecyclerItem>>()
    val noteLiveData : LiveData<MutableList<RecyclerItem>> = _noteLiveData

    private val _noteEditResult = MutableLiveData<GenericResult>()
    val noteEditResult : LiveData<GenericResult> = _noteEditResult

    private val _imageElementRequest = MutableLiveData<ImageElementRequest>()
    val imageElementRequest : LiveData<ImageElementRequest> = _imageElementRequest

    var editable: Boolean = false

    var noteId: Long? = null
    set(id) {
        field = id
        field?.let { fetchNote(it) }
    }

    private val deletedElements = emptyList<NoteElement>().toMutableList()

    private fun toViewModel(noteElement: NoteElement) : NoteElementViewModel {
        val elementActionListener = ElementActionListener(
                onMoveUp = ::moveUpElement,
                onMoveDown = ::moveDownElement,
                onDelete = ::deleteElement
        )

        return when (noteElement) {
            is TextNoteElement -> TextNoteElementViewModel(noteElement, elementActionListener)
            is ListNoteElement -> ListNoteElementViewModel(noteElement, _noteLiveData, editable, elementActionListener)
            is ImageNoteElement ->  ImageNoteElementViewModel(noteElement, elementActionListener, onEditImage = ::requestImageEdit)
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

    private fun constructElement(type: Int, position: Int) : NoteElement {
        return when (type) {
            0 -> TextNoteElement(orderNumber = position, title = "", contents = "")
            1 -> ListNoteElement(orderNumber = position, title = "",
                    entries = emptyList<ListNoteElementEntry>().toMutableList())
            2 -> ImageNoteElement(orderNumber = position, title = "", sourcePath = "")
            else -> TODO()
        }
    }

    fun addElement(type: Int) {
        val recyclerItems : MutableList<RecyclerItem> = _noteLiveData.value ?: return
        val newElement : NoteElement = constructElement(type, recyclerItems.size + 1)

        val noteRecyclerItem : RecyclerItem = recyclerItems[0]
        val note = noteRecyclerItem.data as Note
        note.elements.add(newElement)

        val elementViewModel = toViewModel(newElement)
        recyclerItems.add(elementViewModel.toRecyclerItem(editable))

        _noteLiveData.value = _noteLiveData.value
    }

    private fun moveUpElement(movedElementViewModel: NoteElementViewModel) {
        val elements : MutableList<RecyclerItem> = _noteLiveData.value ?: return
        val index : Int = elements.indexOfFirst {
            it.data == movedElementViewModel
        }
        if (index <= 1) {
            return
        }

        val prevElement = elements[index - 1]
        elements[index - 1] = elements[index]
        elements[index] = prevElement

        val movedElementPosition = index + 1

        val prevElementViewModel = prevElement.data as NoteElementViewModel
        prevElementViewModel.model.orderNumber = movedElementPosition
        movedElementViewModel.model.orderNumber = movedElementPosition - 1

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

        val movedElementPosition = index + 1

        val nextElementViewModel = nextElement.data as NoteElementViewModel
        nextElementViewModel.model.orderNumber = movedElementPosition
        elementViewModel.model.orderNumber = movedElementPosition + 1

        _noteLiveData.value = _noteLiveData.value
    }

    private fun deleteElement(elementViewModel: NoteElementViewModel) {
        val recyclerItems : MutableList<RecyclerItem> = _noteLiveData.value ?: return

        recyclerItems.removeIf { it.data == elementViewModel }

        val note = recyclerItems[0].data as Note
        deletedElements.add(elementViewModel.model)
        note.elements.remove(elementViewModel.model)

        var orderNumber = 1
        val noteElements : List<NoteElement> = recyclerItems
                .map {it.data}
                .filterIsInstance<NoteElementViewModel>()
                .map {it.model}
        for (element in noteElements) {
            element.orderNumber = orderNumber++
        }

        _noteLiveData.value = _noteLiveData.value
    }

    fun fetchNote(noteId: Long) = viewModelScope.launch {
        val noteResponse = async {notesService.getNoteById(noteId) }
        val elementsResponse = async { elementsRepository.getByNoteId(noteId) }

        val note : Note = noteResponse.await().body() ?: return@launch
        val elements = elementsResponse.await().body() ?: return@launch
        note.elements = elements.toMutableList()

        val recyclerItems = ArrayList<RecyclerItem>()
        recyclerItems.add(note.toRecyclerItem())
        recyclerItems.addAll(note.elements
                .sortedBy { it.orderNumber }
                .map { toViewModel(it) }
                .map { it.toRecyclerItem(editable) })

        _noteLiveData.value = recyclerItems
    }

    fun editNote() {
        val recyclerItems: List<RecyclerItem> = _noteLiveData.value ?: return
        val note : Note = (recyclerItems[0].data as Note)
        val noteRequest = NoteRequest(
                id = note.id,
                name = note.name,
                description = note.description,
                categoryId = note.categoryId,
                thumbnail = note.thumbnail)

        val noteElements : List<NoteElement> = recyclerItems
                .subList(1, recyclerItems.size)
                .map { it.data }
                .filterIsInstance<NoteElementViewModel>()
                .map { it.model }

        viewModelScope.launch {
            val editedNote = notesService.edit(noteRequest)
            elementsRepository.delete(deletedElements)
            val editedNotes = elementsRepository.createOrEdit(note.id, noteElements)

            if (editedNote.body() != null && editedNotes.size != noteElements.size) {
                _noteEditResult.value = GenericResult(success = 1)
            } else {
                _noteEditResult.value = GenericResult(error = 1)
            }
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

    fun uploadImage(contentRequest: ContentUriRequestBody) = viewModelScope.launch {
        val multipart = MultipartBody.Part
                .createFormData("file", contentRequest.filename, contentRequest)
        val response = fileService.postFile(multipart)
        val fileUrl = response.body()?.fileDownloadUri
        fileUrl?.let {
            _imageElementRequest.value = ImageElementRequest(
                    _imageElementRequest.value!!.recyclerItemIndex, it)
        }
    }

    private fun requestImageEdit(imageViewModel: ImageNoteElementViewModel) {
        val index = _noteLiveData.value?.indexOfFirst { recyclerItem ->
            recyclerItem.data == imageViewModel
        }
        index?.let {
            _imageElementRequest.value = ImageElementRequest(index)
        }
    }

    fun editImage(index: Int, contentUrl: String) {
        val recyclerItems: MutableList<RecyclerItem> = _noteLiveData.value ?: return
        viewModelScope.launch {
            val recyclerItem: RecyclerItem = recyclerItems[index]
            val imageViewModel = recyclerItem.data as ImageNoteElementViewModel
            val imageElement = imageViewModel.imageElement
            imageElement.sourcePath = contentUrl
            _noteLiveData.value = _noteLiveData.value
        }
    }

    fun canCurrentUserEdit(ownerProfileId: Long): Boolean {
        if (currentUser.profileId == ownerProfileId) {
            return true
        }
        if (currentUser.userRoles.any {it.contains("moderator")}) {
            return true
        }
        return false
    }
}