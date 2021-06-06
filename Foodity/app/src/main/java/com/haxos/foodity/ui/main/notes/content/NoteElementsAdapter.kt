package com.haxos.foodity.ui.main.notes.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.*
import com.koushikdutta.ion.Ion

class NoteElementsAdapter(
    private var note: Note? = null,
    private var editable: Boolean = false,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return R.layout.recyclerview_note_header
        }

        return when (note?.elements?.get(position - 1)) {
            is TextNoteElement -> R.layout.recyclerview_element_text
            is ListNoteElement -> R.layout.recyclerview_element_list
            is ImageNoteElement -> R.layout.recyclerview_element_image
            else -> TODO()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.recyclerview_note_header -> NoteHeaderViewHolder(view)
            R.layout.recyclerview_element_text -> TextElementViewHolder(view)
            R.layout.recyclerview_element_list -> ListElementViewHolder(view)
            R.layout.recyclerview_element_image -> ImageElementViewHolder(view)
            else -> TODO()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            val headerHolder = holder as NoteHeaderViewHolder
            headerHolder.name.text = note?.name
            headerHolder.description.text = note?.description
            return
        }

        when (val element = note?.elements?.get(position - 1)) {
            is TextNoteElement -> {
                val textHolder = holder as TextElementViewHolder
                textHolder.title.text = element.title
                textHolder.contents.text = element.contents
            }
            is ListNoteElement -> {
                val listHolder = holder as ListElementViewHolder
                listHolder.title.text = element.title
                listHolder.entries.adapter = ListElementEntriesAdapter(element.entries)
            }
            is ImageNoteElement -> {
                val imageHolder = holder as ImageElementViewHolder
                imageHolder.title.text = element.title
                Ion.with(imageHolder.image)
                    .placeholder(R.drawable.bg_grey_rounded)
                    .error(R.drawable.foodity_logo)
                    .load(element.sourcePath)
            }
            else -> TODO()
        }
    }

    override fun getItemCount(): Int {
        val note : Note = note ?: return 0
        return note.elements.size + 1
    }

    fun setNote(note: Note) {
        this.note = note
        notifyDataSetChanged()
    }
}