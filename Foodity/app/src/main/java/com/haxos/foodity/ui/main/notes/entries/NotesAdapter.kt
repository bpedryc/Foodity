package com.haxos.foodity.ui.main.notes.entries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NoteCategory

class NotesAdapter (
    private var notes: ArrayList<NoteCategory>
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView) {
        val categoryName: TextView = categoryView.findViewById(R.id.note_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view_note, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = notes[position].name
    }

    override fun getItemCount(): Int = notes.size


}