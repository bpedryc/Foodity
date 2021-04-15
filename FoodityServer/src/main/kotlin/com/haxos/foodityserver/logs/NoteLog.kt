package com.haxos.foodityserver.logs

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.JPAPersistable
import com.haxos.foodityserver.notes.Note
import com.haxos.foodityserver.profiles.Profile
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class NoteLog (
    val type: Type,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    val profile: Profile,

    @JsonBackReference
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
