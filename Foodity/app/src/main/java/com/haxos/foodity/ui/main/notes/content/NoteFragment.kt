package com.haxos.foodity.ui.main.notes.content

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNoteBinding
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment: Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance (noteId: Long): NoteFragment {
            val args = Bundle()
            args.putLong("noteId", noteId)
            val fragment = NoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding : FragmentNoteBinding
    @Inject lateinit var noteViewModel: NoteViewModel

    lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteBinding.inflate(inflater)

        toolbar = binding.toolbarNote
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        setHasOptionsMenu(true)
        toolbar.setOnMenuItemClickListener(this)

        val elementsAdapter = NoteElementsAdapter(editable = false)
        binding.recyclerviewNoteelements.adapter = elementsAdapter
        binding.recyclerviewNoteelements.layoutManager = object : LinearLayoutManager(context) {
            override fun isAutoMeasureEnabled(): Boolean = true
        }

        noteViewModel.noteLiveData.observe(viewLifecycleOwner, {
            if (it == null) {
                activity?.onBackPressed()
                return@observe
            }

//            binding.noteName.text = it.name
//            binding.noteDescription.text = it.description
            elementsAdapter.setNote(it)
        })

        val noteId: Long? = arguments?.getLong("noteId")
        noteViewModel.noteId = noteId

        return binding.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_edit -> onNoteEdit()
            R.id.action_note_delete -> onNoteDelete()
        }
        return true
    }

    private fun onNoteEdit() {
        noteViewModel.noteId?.let {
            val editFragment = NoteEditFragment.newInstance(it)
            replace(editFragment)
        }
    }

    private fun onNoteDelete() {
        AlertDialog.Builder(activity)
            .setTitle("Deleting the note")
            .setMessage("Are you sure you want to delete the note?")
            .setPositiveButton(android.R.string.yes) {_, _ -> noteViewModel.deleteNote()}
            .setNegativeButton(android.R.string.no) {_, _ -> }
            .show()
    }
}