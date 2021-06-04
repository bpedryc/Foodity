package com.haxos.foodityserver

import com.haxos.foodityserver.rest.notes.note.INotesRepository
import com.haxos.foodityserver.rest.notes.noteelement.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NoteElementsTests (
    @Autowired val noteRepository: INotesRepository,
    @Autowired val textElementRepository: ITextNoteElementRepository,
    @Autowired val listElementRepository: IListNoteElementRepository,
    @Autowired val imageElementRepository: IImageNoteElementRepository
){
    @Test
    fun contextLoads() {
        val note = noteRepository.getOne(22)

        textElementRepository.deleteAll()
        listElementRepository.deleteAll()
        imageElementRepository.deleteAll()


        /*arrayListOf(
            ListNoteElementEntry(0, "Banan"),
            ListNoteElementEntry(1, "Truskawki"),
            ListNoteElementEntry(2, "Maliny")
        )*/

        val textElement = TextNoteElement("Przygotowanie", 0, "Przygotuj w następujący sposób:....", note)
        val listElement = ListNoteElement("Składniki", 1, arrayListOf(), note)
        val imageElement = ImageNoteElement("Gotowe danie", 3, "/testimage.jpg", note)
        val textElement2 = TextNoteElement("Dodatkowe informacje", 4, "Warto wiedzieć również, że:....", note)

        textElementRepository.saveAll(arrayListOf(textElement, textElement2))
        listElementRepository.save(listElement)
        imageElementRepository.save(imageElement)

    }
}