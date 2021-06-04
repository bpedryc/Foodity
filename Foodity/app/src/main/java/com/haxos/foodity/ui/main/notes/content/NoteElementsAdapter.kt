package com.haxos.foodity.ui.main.notes.content

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NoteElement

class NoteElementsAdapter(
    private var noteElements: List<NoteElement> = ArrayList(),
    private var editable: Boolean = false,
) : RecyclerView.Adapter<NoteElementViewHolder>() {

    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteElementViewHolder {
        val binder = noteElements[viewType].getAdapter()
        return binder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NoteElementViewHolder, position: Int) {
        val noteElement = noteElements[position]
        holder.bind()
    }

    override fun getItemCount(): Int = noteElements.size
    fun setElements(elements: List<NoteElement>) {
        noteElements = elements
        notifyDataSetChanged()
    }
}


abstract class NoteElementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    protected val title: TextView = view.findViewById(R.id.noteelement_title)
    abstract fun bind()
}

/*
class ListViewHolder(view: View) : NoteElementViewHolder(view) {
    val listView : ListView = view.findViewById(R.id.noteelement_list_listview)

    override fun fill(element: NoteElement) {
        super.fill(element)
        listView.
    }
}

class ImageViewHolder(view: View) : NoteElementViewHolder(view) {
    val imageView : ImageView = view.findViewById(R.id.noteelement_image_imageview)

    override fun fill(element: NoteElement) {
        super.fill(element)
    }
}*/
