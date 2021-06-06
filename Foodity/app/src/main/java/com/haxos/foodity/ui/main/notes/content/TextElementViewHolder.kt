package com.haxos.foodity.ui.main.notes.content

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R

class TextElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.element_text_title)
    val contents: TextView = itemView.findViewById(R.id.element_text_contents)
}