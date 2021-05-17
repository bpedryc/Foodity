package com.haxos.foodity.ui.main.notes.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NoteElement

class NoteElementsAdapter(
    private var noteElements: List<NoteElement> = ArrayList()
) : RecyclerView.Adapter<NoteElementsAdapter.ViewHolder>() {

    inner class ViewHolder(elementView: View) : RecyclerView.ViewHolder(elementView) {
        val title: TextView = elementView.findViewById(R.id.noteelement_title)
        val contents: TextView = elementView.findViewById(R.id.noteelement_contents)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_noteelements, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noteElement = noteElements[position]
        holder.title.text = noteElement.title
        holder.contents.text = noteElement.contents
    }

    override fun getItemCount(): Int = noteElements.size
    fun setElements(elements: List<NoteElement>) {
        noteElements = elements
        notifyDataSetChanged()
    }

}
