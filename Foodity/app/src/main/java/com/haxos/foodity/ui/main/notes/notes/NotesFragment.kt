package com.haxos.foodity.ui.main.notes.notes

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.databinding.FragmentNotesBinding
import com.haxos.foodity.ui.main.notes.content.NoteFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import com.haxos.foodity.ui.main.notes.notesearch.NotesSearchingToolbar
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment: Fragment(), INoteSearchingFragment, NotesAdapter.INoteContextListener {

    companion object {
        fun newInstance(categoryId: Long, profileId: Long): NotesFragment {
            val args = Bundle()
            args.putLong("categoryId", categoryId)
            args.putLong("profileId", profileId)
            val fragment = NotesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: FragmentNotesBinding
    @Inject lateinit var notesViewModel: NotesViewModel

    var noteCreationDialog: AlertDialog? = null
    private var selectedNoteId: Int = -1

    override var profileId: Long? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotesBinding.inflate(inflater)

        val toolbar: Toolbar = binding.toolbarFragmentNotes
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        val notesSearchingToolbar = NotesSearchingToolbar(toolbar, this)

        val notesRecyclerView: RecyclerView = binding.recyclerviewNotes
        notesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val categoryId: Long = arguments?.getLong("categoryId")
                ?: return binding.root

        profileId = arguments?.getLong("profileId")
        val profileId = profileId ?: return binding.root

        val notesAdapter = NotesAdapter(
            clickListener = NoteClickListener(profileId), contextListener = this)
        notesRecyclerView.adapter = notesAdapter
        notesViewModel.notesLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.backgroundText.visibility = View.VISIBLE
            } else {
                binding.backgroundText.visibility = View.GONE
            }
            notesAdapter.setNotes(it)
        })

        notesViewModel.fetchNotes(categoryId)

        if (notesViewModel.isCurrentProfile(profileId)) {
            toolbar.inflateMenu(R.menu.menu_notes)
            toolbar.setOnMenuItemClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
                        .setView(R.layout.dialog_note)
                        .setTitle(getString(R.string.dialog_note_title))
                        .setMessage(getString(R.string.dialog_note_message))
                        .setPositiveButton(android.R.string.yes) {_, _ -> createNote()}
                        .setNegativeButton(android.R.string.no) {_, _ -> }
                noteCreationDialog = builder.show()
                true
            }

            registerForContextMenu(binding.recyclerviewNotes)
        }


        return binding.root
    }

    private fun createNote() {
        val nameEditText = noteCreationDialog?.findViewById<EditText>(R.id.note_name) ?: return
        val name = nameEditText.text.toString()
        notesViewModel.createNote(name)
    }

    inner class NoteClickListener(val profileId: Long) : NotesAdapter.INoteClickListener {
        override fun onClick(note: Note) {
            val noteContentFragment = NoteFragment.newInstance(note.id, profileId)
            replace(noteContentFragment)
        }
    }

    override val noteSearchingViewModel: INoteSearchingViewModel
        get() = notesViewModel
    override val noteSearchRecyclerView: RecyclerView
        get() = binding.recyclerviewSearch

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = requireActivity().menuInflater
        inflater.inflate(R.menu.contextmenu_note, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_note_duplicate -> {
                notesViewModel.duplicateNote(selectedNoteId.toLong())
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onNoteContextClick(view: View, id: Int) {
        selectedNoteId = id
        view.showContextMenu()
    }
}