package com.haxos.foodity.data.model

abstract class NoteElement (
        var id: Long?,
        var orderNumber: Int,
        var title: String
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is NoteElement) {
            return false
        }

        if (id == other.id &&
            orderNumber == other.orderNumber &&
            title == other.title) {
            return true
        }
        return false
    }
}