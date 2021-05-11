package com.haxos.foodityserver.rest.notes.note

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.notes.NoteElement
import com.haxos.foodityserver.rest.notes.category.NotesCategory
import javax.persistence.*

@Entity
data class Note (
    val name: String,
    val description: String,
    val thumbnail: Int,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: NotesCategory,

    @OneToMany()
    @JoinColumn(name = "note_id")
    val elements: List<NoteElement> = ArrayList()

) : JPAPersistable<Long>()