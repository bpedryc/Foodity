package com.haxos.foodity.data.model

import android.media.Image

data class Note (
        val id: Long,
        val name: String,
        val photos: List<Image>,
        val elements: List<NoteElement>
        )