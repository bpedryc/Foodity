package com.haxos.foodity.ui.main.notes

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.ui.main.SearchResultAdapter
import com.haxos.foodity.ui.settings.SettingsActivity
import com.haxos.foodity.ui.utils.replace

class NotesSearchingToolbar(
    val toolbar: Toolbar,
    val fragment: NotesFragment
) {

    init {
//        val binding = fragment.binding
        val context : Context = fragment.requireContext()
        val notesViewModel : INoteSearchingViewModel = fragment.notesViewModel
        val viewLifecycleOwner = fragment.viewLifecycleOwner

        toolbar.setNavigationOnClickListener {
            context.startActivity(Intent(fragment.activity, SettingsActivity::class.java))
        }

        val searchView = toolbar.findViewById<SearchView>(R.id.searchview_notes)//binding.notesSearchView
        searchView.setOnQueryTextListener(notesViewModel.searchListener)

        val searchRecyclerView = fragment.notesRecyclerView
        searchRecyclerView.layoutManager = LinearLayoutManager(context)

        val searchAdapter = object : SearchResultAdapter(clickListener = NoteSearchClickListener()) {
            override fun getTextToDisplay(objectToDisplay: Any): String {
                return (objectToDisplay as Note).name
            }
        }
        searchRecyclerView.adapter = searchAdapter
        notesViewModel.searchLiveData.observe(viewLifecycleOwner, {
            searchAdapter.setItems(it)
        })
    }

    inner class NoteSearchClickListener : SearchResultAdapter.IItemClickListener {
        override fun onItemClick(item: Any) {
            val note = item as Note
            fragment.replace(NoteContentFragment.newInstance(note.id))
        }
    }
}