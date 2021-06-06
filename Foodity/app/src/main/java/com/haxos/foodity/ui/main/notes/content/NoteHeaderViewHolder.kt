package com.haxos.foodity.ui.main.notes.content

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R

class NoteHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.note_name)
    val description: TextView = itemView.findViewById(R.id.note_description)
}
