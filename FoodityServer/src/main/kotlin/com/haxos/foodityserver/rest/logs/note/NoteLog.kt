package com.haxos.foodityserver.rest.logs.note

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.notes.note.Note
import com.haxos.foodityserver.rest.profiles.Profile
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class NoteLog (
    val type: Type,

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    val profile: Profile,

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    val target: Note,

    val timestamp: LocalDateTime
) : JPAPersistable<Long>() {
    enum class Type {
        CREATE,
        EDIT,
        DELETE,
        LIKE,
        COMMENT
    }
}
