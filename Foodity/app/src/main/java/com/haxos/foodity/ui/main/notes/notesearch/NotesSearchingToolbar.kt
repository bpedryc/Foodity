package com.haxos.foodity.ui.main.notes.notesearch

import android.content.Intent
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.ui.main.search.SearchResultAdapter
import com.haxos.foodity.ui.main.notes.content.NoteContentFragment
import com.haxos.foodity.ui.settings.SettingsActivity
import com.haxos.foodity.utils.replace

class NotesSearchingToolbar(
    val toolbar: Toolbar,
    val searchingFragment: INoteSearchingFragment
) {

    init {
        val fragment = searchingFragment as Fragment
        val viewLifecycleOwner = fragment.viewLifecycleOwner
        val noteSearchingViewModel : INoteSearchingViewModel = searchingFragment.noteSearchingViewModel

        val searchView = toolbar.findViewById<SearchView>(R.id.searchview_notes)
        searchView.setOnQueryTextListener(noteSearchingViewModel.searchListener)

        val searchRecyclerView : RecyclerView = searchingFragment.noteSearchRecyclerView
        searchRecyclerView.layoutManager = LinearLayoutManager(fragment.context)

        val searchAdapter = object : SearchResultAdapter(clickListener = NoteSearchClickListener()) {
            override fun getTextToDisplay(objectToDisplay: Any): String {
                return (objectToDisplay as Note).name
            }
        }
        searchRecyclerView.adapter = searchAdapter
        noteSearchingViewModel.searchLiveData.observe(viewLifecycleOwner, {
            searchAdapter.setItems(it)
        })
    }

    inner class NoteSearchClickListener : SearchResultAdapter.IItemClickListener {
        override fun onItemClick(item: Any) {
            val note = item as Note
            (searchingFragment as Fragment).replace(NoteContentFragment.newInstance(note.id))
        }
    }
}