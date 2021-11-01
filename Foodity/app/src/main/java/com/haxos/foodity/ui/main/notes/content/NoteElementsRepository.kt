package com.haxos.foodity.ui.main.notes.content

import com.haxos.foodity.data.elementRequest.ImageNoteElementRequest
import com.haxos.foodity.data.elementRequest.ListNoteElementRequest
import com.haxos.foodity.data.elementRequest.TextNoteElementRequest
import com.haxos.foodity.data.model.ImageNoteElement
import com.haxos.foodity.data.model.ListNoteElement
import com.haxos.foodity.data.model.NoteElement
import com.haxos.foodity.data.model.TextNoteElement
import com.haxos.foodity.retrofit.services.INoteElementService
import retrofit2.Response
import javax.inject.Inject

class NoteElementsRepository @Inject constructor(
        private val elementService: INoteElementService
){
    suspend fun getByNoteId(noteId: Long): Response<List<NoteElement>> {
        return elementService.getByNoteId(noteId)
    }

    suspend fun delete(noteElements: List<NoteElement>): Boolean {
        val deleteResults = noteElements.map { delete(it) }
        if (deleteResults.any { false }) {
            return false
        }
        return true
    }

    suspend fun createOrEdit(noteId: Long, elements: List<NoteElement>): List<NoteElement> {
        val editedElements = emptyList<NoteElement>().toMutableList()
        elements.forEach { element ->
            createOrEdit(noteId, element)?.let {
                editedElements.add(it)
            }
        }
        return editedElements
    }

    private suspend fun createOrEdit(noteId: Long, element: NoteElement) : NoteElement? {
        return when (element) {
            is TextNoteElement -> createOrEdit(noteId, element)
            is ImageNoteElement -> createOrEdit(noteId, element)
            is ListNoteElement -> createOrEdit(noteId, element)
            else -> null
        }
    }

    private suspend fun createOrEdit(noteId: Long, textElement: TextNoteElement) : TextNoteElement? {
        val textRequest = TextNoteElementRequest(
            textElement.id, textElement.title, textElement.orderNumber, textElement.contents, noteId)
        if (textElement.id != null) {
            val response = elementService.edit(textRequest)
            return response.body()
        }
        val response = elementService.create(textRequest)
        return response.body()
    }

    private suspend fun createOrEdit(noteId: Long, listElement: ListNoteElement) : ListNoteElement? {
        val listRequest = ListNoteElementRequest(
            listElement.id, listElement.title, listElement.orderNumber, listElement.entries, noteId)
        if (listElement.id != null) {
            val response = elementService.edit(listRequest)
            return response.body()
        }
        val response = elementService.create(listRequest)
        return response.body()
    }

    private suspend fun createOrEdit(noteId: Long, imageElement: ImageNoteElement) : ImageNoteElement? {
        val imageRequest = ImageNoteElementRequest(
            imageElement.id, imageElement.title, imageElement.orderNumber, imageElement.sourcePath, noteId)
        if (imageElement.id != null) {
            val response = elementService.edit(imageRequest)
            return response.body()
        }
        val response = elementService.create(imageRequest)
        return response.body()
    }

    private suspend fun delete(element: NoteElement) : Boolean {
        return when (element) {
            is TextNoteElement -> delete(element)
            is ImageNoteElement -> delete(element)
            is ListNoteElement -> delete(element)
            else -> false
        }
    }

    private suspend fun delete(textElement: TextNoteElement) : Boolean {
        val id = textElement.id ?: return false
        val response = elementService.deleteTextElement(id)
        return response.body() ?: false
    }

    private suspend fun delete(imageElement: ImageNoteElement) : Boolean {
        val id = imageElement.id ?: return false
        val response = elementService.deleteImageElement(id)
        return response.body() ?: false
    }

    private suspend fun delete(listElement: ListNoteElement) : Boolean {
        val id = listElement.id ?: return false
        val response = elementService.deleteListElement(id)
        return response.body() ?: false
    }
}
