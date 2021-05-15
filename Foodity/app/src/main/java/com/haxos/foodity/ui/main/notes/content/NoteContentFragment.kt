package com.haxos.foodity.ui.main.notes.content

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNoteContentBinding
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteContentFragment: Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance (noteId: Long): NoteContentFragment {
            val args = Bundle()
            args.putLong("noteId", noteId)
            val fragment = NoteContentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding : FragmentNoteContentBinding
    @Inject lateinit var noteContentViewModel: NoteContentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteContentBinding.inflate(inflater)

        val toolbar = binding.toolbarNotecontent
        setHasOptionsMenu(true)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        toolbar.setOnMenuItemClickListener(this)

        noteContentViewModel.noteLiveData.observe(viewLifecycleOwner, {
            if (it == null) {
                activity?.onBackPressed()
                return@observe
            }

            binding.noteName.text = it.name
            binding.noteDescription.text = it.description
        })

        val noteId: Long? = arguments?.getLong("noteId")
        noteContentViewModel.noteId = noteId

        return binding.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_edit -> {
                Toast.makeText(context, "EDIT", Toast.LENGTH_SHORT).show()
            }
            R.id.action_note_delete -> onNoteDelete()
        }
        return true
    }

    private fun onNoteDelete() {
        AlertDialog.Builder(activity)
            .setTitle("Deleting the note")
            .setMessage("Are you sure you want to delete the note?")
            .setPositiveButton(android.R.string.yes) {_, _ -> noteContentViewModel.deleteNote()}
            .setNegativeButton(android.R.string.no) {_, _ -> }
            .show()
    }

}