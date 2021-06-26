package com.haxos.foodityserver.rest.notes.noteelement.list

import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElement
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

        request.entries.forEach { entryRequest ->
            val entry = element.entries?.find {
                it.getId()!! == entryRequest.getId()
            } ?: return@forEach
            entry.contents = entryRequest.contents
            entry.orderNumber = entryRequest.orderNumber
        }

        return repository.save(element)
    }
}