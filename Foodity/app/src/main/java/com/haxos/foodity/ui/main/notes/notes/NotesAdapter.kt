package com.haxos.foodity.ui.main.notes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note

class NotesAdapter (
    private var notes: List<Note> = ArrayList(),
    private var clickListener: INoteClickListener
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView) {
        val categoryName: TextView = categoryView.findViewById(R.id.note_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gridview_note, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.categoryName.text = note.name
        holder.itemView.setOnClickListener {
            clickListener.onClick(note)
        }
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notesToSet : List<Note>) {
        notes = notesToSet
        notifyDataSetChanged()
    }

    interface INoteClickListener {
        fun onClick(note: Note)
    }
}