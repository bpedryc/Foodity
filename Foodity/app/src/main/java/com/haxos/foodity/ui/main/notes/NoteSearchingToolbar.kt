package com.haxos.foodity.ui.main.notes

import android.content.Context
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R


class NoteSearchingToolbar(context: Context) : Toolbar(context) {

    init {
        val searchView = findViewById<SearchView>(R.id.searchview_notes)
        ViewModelProvider.of()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_notes)


    }
}