package com.haxos.foodity.ui.main.notes.content

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNoteEditBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteEditFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance (noteId: Long): NoteEditFragment {
            val args = Bundle()
            args.putLong("noteId", noteId)
            val fragment = NoteEditFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding : FragmentNoteEditBinding
    @Inject lateinit var noteViewModel : NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteEditBinding.inflate(inflater)

        val toolbar = binding.toolbarNoteEdit
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        setHasOptionsMenu(true)
        toolbar.setOnMenuItemClickListener(this)

        val popupMenu = PopupMenu(requireContext(), binding.actionNoteelementAdd)
        popupMenu.menu.add("Text")
        popupMenu.menu.add("List")
        popupMenu.menu.add("Image")
        binding.actionNoteelementAdd.setOnClickListener {
            popupMenu.show()
        }

        noteViewModel.editable = true
        noteViewModel.noteId = arguments?.getLong("noteId")

        return binding.also {
            it.viewModel = noteViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_confirm -> onNoteEditConfirm()
        }
        return true
    }

    private fun onNoteEditConfirm() {
        noteViewModel.noteEditResult.observe(viewLifecycleOwner, {
            requireActivity().onBackPressed()
        })

        noteViewModel.editNote()

        /*noteViewModel.editNote(
            binding.noteName.text.toString(),
            binding.noteDescription.text.toString()
        )*/
    }
}