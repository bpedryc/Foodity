package com.haxos.foodity.ui.main.notes.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.databinding.FragmentNotesBinding
import com.haxos.foodity.ui.main.notes.notesearch.NotesSearchingToolbar
import com.haxos.foodity.ui.main.notes.content.NoteFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment: Fragment(), INoteSearchingFragment {

    companion object {
        fun newInstance(categoryId: Long): NotesFragment {
            val args = Bundle()
            args.putLong("categoryId", categoryId)
            val fragment = NotesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: FragmentNotesBinding
    @Inject lateinit var notesGridViewModel: NotesGridViewModel

    var noteCreationDialog: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotesBinding.inflate(inflater)

        val toolbar: Toolbar = binding.toolbarFragmentNotes
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        toolbar.inflateMenu(R.menu.menu_notes)
        toolbar.setOnMenuItemClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
                .setView(R.layout.dialog_note)
                .setTitle("New note")
                .setMessage("Create a note")
                .setPositiveButton(android.R.string.yes) {_, _ -> createNote()}
                .setNegativeButton(android.R.string.no) {_, _ -> }
            noteCreationDialog = builder.show()
            true
        }
        val notesSearchingToolbar = NotesSearchingToolbar(toolbar, this)

        val notesRecyclerView: RecyclerView = binding.recyclerviewNotes
        notesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val notesAdapter = NotesAdapter(clickListener = NoteClickListener())
        notesRecyclerView.adapter = notesAdapter
        notesGridViewModel.notesLiveData.observe(viewLifecycleOwner, {
            notesAdapter.setNotes(it)
        })

        val categoryId: Long? = arguments?.getLong("categoryId")
        if (categoryId != null) {
            notesGridViewModel.fetchNotes(categoryId)
        }

        return binding.root
    }

    private fun createNote() {
        val nameEditText = noteCreationDialog?.findViewById<EditText>(R.id.note_name) ?: return
        val name = nameEditText.text.toString()
        notesGridViewModel.addNote(name)
    }

    inner class NoteClickListener : NotesAdapter.INoteClickListener {
        override fun onClick(note: Note) {
            val noteContentFragment = NoteFragment.newInstance(note.id)
            replace(noteContentFragment)
        }
    }

    override val noteSearchingViewModel: INoteSearchingViewModel
        get() = notesGridViewModel
    override val noteSearchRecyclerView: RecyclerView
        get() = binding.recyclerviewSearch
}