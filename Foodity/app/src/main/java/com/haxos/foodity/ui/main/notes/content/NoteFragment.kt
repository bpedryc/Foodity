package com.haxos.foodity.ui.main.notes.content

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNoteBinding
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment: Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance (noteId: Long, profileId: Long): NoteFragment {
            val args = Bundle()
            args.putLong("noteId", noteId)
            args.putLong("profileId", profileId)

            val fragment = NoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding : FragmentNoteBinding
    @Inject lateinit var noteViewModel: NoteViewModel

    var noteId: Long = 0

    lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteBinding.inflate(inflater)

        toolbar = binding.toolbarNote
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        noteViewModel.noteLiveData.observe(viewLifecycleOwner) {
            if (it == null) {
                requireActivity().onBackPressed()
            }
        }

        val bindingRoot = binding.also {
            it.viewModel = noteViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root

        noteId = arguments?.getLong("noteId")
            ?: return bindingRoot
        val ownerProfileId: Long = arguments?.getLong("profileId")
            ?: return bindingRoot

        if (noteViewModel.canCurrentUserEdit(ownerProfileId)) {
            toolbar.inflateMenu(R.menu.menu_note)
            toolbar.setOnMenuItemClickListener(this)
            setHasOptionsMenu(true)
        }

        noteViewModel.fetchNote(noteId)

        return bindingRoot
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_edit -> onNoteEdit()
            R.id.action_note_delete -> onNoteDelete()
        }
        return true
    }

    private fun onNoteEdit() {
        val editFragment = NoteEditFragment.newInstance(noteId)
        replace(editFragment)
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