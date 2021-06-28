package com.haxos.foodityserver.rest.notes.note

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.notes.category.NotesCategory
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Note (
    var name: String? = null,
    var description: String? = null,
    var thumbnail: Int = 0,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: NotesCategory? = null

) : JPAPersistable<Long>()