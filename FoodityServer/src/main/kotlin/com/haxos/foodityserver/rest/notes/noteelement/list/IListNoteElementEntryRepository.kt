package com.haxos.foodityserver.rest.notes.noteelement.list

import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IListNoteElementEntryRepository : JpaRepository<ListNoteElement, Long> {
}