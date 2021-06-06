package com.haxos.foodity.ui.main.notes.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.ImageNoteElement
import com.haxos.foodity.data.model.ListNoteElement
import com.haxos.foodity.data.model.NoteElement
import com.haxos.foodity.data.model.TextNoteElement
import com.koushikdutta.ion.Ion

class NoteElementsAdapter(
    private var noteElements: List<NoteElement> = ArrayList(),
    private var editable: Boolean = false,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        when (noteElements[position]) {
            is TextNoteElement -> R.layout.recyclerview_element_text
            is ListNoteElement -> R.layout.recyclerview_element_list
            is ImageNoteElement -> R.layout.recyclerview_element_image
            else -> TODO()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.recyclerview_element_text -> TextElementViewHolder(view)
            R.layout.recyclerview_element_list -> ListElementViewHolder(view)
            R.layout.recyclerview_element_image -> ImageElementViewHolder(view)
            else -> TODO()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val element = noteElements[position]) {
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

    override fun getItemCount(): Int = noteElements.size
    fun setElements(elements: List<NoteElement>) {
        noteElements = elements
        notifyDataSetChanged()
    }
}