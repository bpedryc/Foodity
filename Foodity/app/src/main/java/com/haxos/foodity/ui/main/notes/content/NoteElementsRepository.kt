package com.haxos.foodity.ui.main.notes.content

import com.haxos.foodity.data.elementRequest.ImageNoteElementRequest
import com.haxos.foodity.data.elementRequest.ListNoteElementRequest
import com.haxos.foodity.data.elementRequest.TextNoteElementRequest
import com.haxos.foodity.data.model.ImageNoteElement
import com.haxos.foodity.data.model.ListNoteElement
import com.haxos.foodity.data.model.NoteElement
import com.haxos.foodity.data.model.TextNoteElement
import com.haxos.foodity.retrofit.INoteElementService
import retrofit2.Response
import javax.inject.Inject

class NoteElementsRepository @Inject constructor(
        private val elementService: INoteElementService
){
    suspend fun edit(elements: List<NoteElement>): List<NoteElement> {
        val editedElements = emptyList<NoteElement>().toMutableList()
        elements.forEach {
            when (it) {
                is TextNoteElement -> {
                    val textRequest = TextNoteElementRequest(it.id!!, it.title, it.orderNumber, it.contents)
                    val response = elementService.edit(textRequest)
                    response.body()?.let { responseBody ->
                        editedElements.add(responseBody)
                    }
                }
                is ImageNoteElement -> {
                    val imageRequest = ImageNoteElementRequest(it.id!!, it.title, it.orderNumber, it.sourcePath)
                    val response = elementService.edit(imageRequest)
                    response.body()?.let { responseBody ->
                        editedElements.add(responseBody)
                    }
                }
                is ListNoteElement -> {
                    val listRequest = ListNoteElementRequest(it.id!!, it.title, it.orderNumber, it.entries)
                    val response = elementService.edit(listRequest)
                    response.body()?.let { responseBody ->
                        editedElements.add(responseBody)
                    }
                }
            }
        }
        return editedElements
    }

    suspend fun getByNoteId(noteId: Long): Response<List<NoteElement>> {
        return elementService.getByNoteId(noteId)
    }
}
