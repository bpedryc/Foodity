package com.haxos.foodity.data.model

import java.time.LocalDateTime

class NoteLog (
    val type: Type,
    val profile: Profile,
    val target: Note,
    val timestamp: LocalDateTime
) {
    enum class Type {
        CREATE,
        EDIT,
        DELETE,
        LIKE,
        COMMENT
    }
}
