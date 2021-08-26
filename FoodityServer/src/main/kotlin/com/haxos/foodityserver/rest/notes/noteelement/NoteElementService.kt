package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.rest.notes.note.Note
import com.haxos.foodityserver.rest.notes.noteelement.image.IImageNoteElementRepository
import com.haxos.foodityserver.rest.notes.noteelement.list.IListNoteElementRepository
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

        elements.sortBy { it.orderNumber }

        return elements
    }

    fun duplicateElements(noteId: Long, newOwnerNote: Note): List<NoteElement> {
        val duplicatedElements = emptyList<NoteElement>().toMutableList()

        val existingNoteElements = getElementsFromNote(noteId)
        existingNoteElements.forEach { existingElement ->
            val elementDuplicate = duplicate(existingElement, newOwnerNote)
            duplicatedElements.add(elementDuplicate)
        }

        return duplicatedElements
    }

    private fun duplicate(element: NoteElement, newOwnerNote: Note) : NoteElement {
        return when (element) {
            is TextNoteElement -> {
                val duplicate = TextNoteElement(element.title, element.orderNumber, newOwnerNote, element.contents)
                textElementRepo.save(duplicate)
            }
            is ListNoteElement -> {
                val duplicateEntries = emptyList<ListNoteElementEntry>().toMutableList()
                element.entries.forEach {
                    val duplicateEntry = ListNoteElementEntry(it.orderNumber, it.contents)
                    duplicateEntries.add(duplicateEntry)
                }
                val duplicate = ListNoteElement(element.title, element.orderNumber, newOwnerNote, duplicateEntries)
                listElementRepo.save(duplicate)
            }
            is ImageNoteElement -> {
                val duplicate = ImageNoteElement(element.title, element.orderNumber, newOwnerNote, element.sourcePath)
                imageElementRepo.save(duplicate)
            }
            else -> TODO()
        }
    }
}
