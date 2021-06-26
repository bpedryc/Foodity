package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.rest.notes.noteelement.image.IImageNoteElementRepository
import com.haxos.foodityserver.rest.notes.noteelement.list.IListNoteElementRepository
import com.haxos.foodityserver.rest.notes.noteelement.text.TextNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.image.ImageNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.list.ListNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.request.NoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.text.ITextNoteElementRepository
import org.springframework.stereotype.Service

@Service
class NoteElementService (
    val textElementRepo: ITextNoteElementRepository,
    val listElementRepo: IListNoteElementRepository,
    val imageElementRepo: IImageNoteElementRepository
){
    fun getElementsFromNote(noteId: Long): List<NoteElement> {
        val textElements = textElementRepo.findByNote(noteId)
        val listElements = listElementRepo.findByNote(noteId)
        val imageElements = imageElementRepo.findByNote(noteId)

        val elements = ArrayList<NoteElement>()
        elements.addAll(textElements)
        elements.addAll(listElements)
        elements.addAll(imageElements)

        return elements
    }

   /* fun editElements(elements: List<NoteElementRequest>): List<NoteElement> {
        val editedElements = emptyList<NoteElement>().toMutableList()

        elements.forEach { element ->
            if (element is TextNoteElementRequest) {
                textElementRepo.findById(element.id).ifPresent {
                    it.title = element.title
                    it.orderNumber = element.order
                    it.contents = element.contents
                    val editedElement = textElementRepo.save(it)
                    editedElements.add(editedElement)
                }
            }
            if (element is ListNoteElementRequest) {
                listElementRepo.findById(element.id).ifPresent {
                    it.title = element.title
                    it.orderNumber = element.order
                    it.entries = element.entries
                    val editedElement = listElementRepo.save(it)
                    editedElements.add(editedElement)
                }
            }
            if (element is ImageNoteElementRequest) {
                imageElementRepo.findById(element.id).ifPresent {
                    it.title = element.title
                    it.orderNumber = element.order
                    it.sourcePath = element.sourcePath
                    val editedElement = imageElementRepo.save(it)
                    editedElements.add(editedElement)
                }
            }
        }

        return editedElements
    }*/

}
