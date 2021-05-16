package com.haxos.foodity.ui.main.notes.content

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNoteContentBinding
import com.haxos.foodity.ui.authentication.login.afterTextChanged
import com.haxos.foodity.utils.hideKeyboard
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

    lateinit var toolbar: Toolbar

    private var editMode : Boolean = false
        set(editMode) {
            if (!editMode){
                activity?.hideKeyboard()

                toolbar.navigationIcon = ContextCompat.getDrawable(activity!!, R.drawable.ic_back)
                toolbar.setNavigationOnClickListener {
                    requireActivity().onBackPressed()
                }
                toolbar.menu.clear()
                toolbar.inflateMenu(R.menu.menu_note_content)

                binding.noteName.isFocusable = false
                binding.noteName.setBackgroundColor(Color.TRANSPARENT)
                binding.noteDescription.isFocusable = false
                binding.noteDescription.setBackgroundColor(Color.TRANSPARENT)
            } else {

                toolbar.navigationIcon = ContextCompat.getDrawable(activity!!, R.drawable.ic_clear)
                toolbar.setNavigationOnClickListener {
                    noteContentViewModel.noteId = arguments?.getLong("noteId")
                    this.editMode = false
                }
                toolbar.menu.clear()
                toolbar.inflateMenu(R.menu.menu_note_content_editable)

                binding.noteName.isFocusableInTouchMode = true
                binding.noteName.setBackgroundColor(Color.LTGRAY)
                binding.noteDescription.isFocusableInTouchMode = true
                binding.noteDescription.setBackgroundColor(Color.LTGRAY)
            }
            field = editMode
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteContentBinding.inflate(inflater)

        toolbar = binding.toolbarNotecontent
        setHasOptionsMenu(true)
        toolbar.setOnMenuItemClickListener(this)

        editMode = false

        noteContentViewModel.noteLiveData.observe(viewLifecycleOwner, {
            if (it == null) {
                activity?.onBackPressed()
                return@observe
            }

            binding.noteName.setText(it.name)
            binding.noteDescription.setText(it.description)
        })

        val noteId: Long? = arguments?.getLong("noteId")
        noteContentViewModel.noteId = noteId

        return binding.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_edit -> onNoteEdit()
            R.id.action_note_delete -> onNoteDelete()
            R.id.action_note_confirm -> onNoteEditConfirm()
        }
        return true
    }

    private fun onNoteEditConfirm() {
        noteContentViewModel.noteEdited(
            binding.noteName.text.toString(),
            binding.noteDescription.text.toString()
        )
        editMode = false
    }

    private fun onNoteEdit() {
        editMode = !editMode
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