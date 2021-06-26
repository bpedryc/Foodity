package com.haxos.foodityserver.rest.notes.noteelement.text

import com.haxos.foodityserver.rest.notes.noteelement.TextNoteElement
import javassist.NotFoundException
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/text-elements")
class TextElementController (
    val repository: ITextNoteElementRepository
){
    @PutMapping
    fun edit(@RequestBody request: TextNoteElementRequest): TextNoteElement {
        val requestId : Long = request.id
        val element = repository.findById(requestId)
            .orElseThrow { NotFoundException("TextNoteElement with id $requestId not found") }

        element.title = request.title
        element.orderNumber = request.orderNumber
        element.contents = request.contents

        return repository.save(element)
    }
}