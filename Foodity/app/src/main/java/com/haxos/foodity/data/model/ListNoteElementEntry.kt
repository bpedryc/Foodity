package com.haxos.foodity.data.model

class ListNoteElementEntry (
        val id: Long? = null,
        var orderNumber: Long,
        var contents: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListNoteElementEntry

        if (id != other.id) return false
        if (orderNumber != other.orderNumber) return false
        if (contents != other.contents) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + orderNumber.hashCode()
        result = 31 * result + contents.hashCode()
        return result
    }

}
