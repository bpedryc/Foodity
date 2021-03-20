package com.haxos.foodityserver.notes

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.JPAPersistable
import javax.persistence.*

@Entity
data class Note (
    val name: String,
    val thumbnail: Int,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: NotesCategory,

    @OneToMany()
    @JoinColumn(name = "note_id")
    val elements: List<NoteElement>

) : JPAPersistable<Long>()