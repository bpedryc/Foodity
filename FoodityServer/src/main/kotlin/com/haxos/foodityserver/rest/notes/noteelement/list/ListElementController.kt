package com.haxos.foodityserver.rest.notes.noteelement.list

import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElement
import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElementEntry
import javassist.NotFoundException
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/list-elements")
class ListElementController (
    val repository: IListNoteElementRepository,
){
    @PutMapping
    fun edit(@RequestBody request: ListNoteElementRequest) : ListNoteElement {
        val requestId : Long = request.id
        val element = repository.findById(requestId)
            .orElseThrow { NotFoundException("ListNoteElement with id $requestId not found") }

        element.title = request.title
        element.orderNumber = request.order

        element.entries.clear()
        element.entries.addAll(request.entries)

        return repository.save(element)
    }
}