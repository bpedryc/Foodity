package com.haxos.foodity.ui.main.notes.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.ListNoteElementEntry

class ListElementEntriesAdapter(
    private var entries: List<ListNoteElementEntry> = ArrayList(),
) : RecyclerView.Adapter<ListElementEntriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_element_list_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.index.text = entry.orderNumber.toString()
        holder.contents.text = entry.contents
    }

    override fun getItemCount(): Int = entries.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val index: TextView = itemView.findViewById(R.id.element_list_entry_index)
        val contents: TextView = itemView.findViewById(R.id.element_list_entry_contents)
    }
}