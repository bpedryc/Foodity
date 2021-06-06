package com.haxos.foodity.ui.main.notes.content

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R

class ListElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.element_list_title)
    val entries: RecyclerView = itemView.findViewById(R.id.element_list_entries)
}