package com.haxos.foodityserver.rest.notes.noteelement

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

}
