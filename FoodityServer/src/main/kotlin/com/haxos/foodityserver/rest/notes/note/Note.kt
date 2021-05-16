package com.haxos.foodityserver.rest.notes.note

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.notes.NoteElement
import com.haxos.foodityserver.rest.notes.category.NotesCategory
import javax.persistence.*

@Entity
data class Note (
    var name: String,
    var description: String,
    var thumbnail: Int,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: NotesCategory,

    @OneToMany()
    @JoinColumn(name = "note_id")
    var elements: List<NoteElement> = ArrayList()

) : JPAPersistable<Long>()