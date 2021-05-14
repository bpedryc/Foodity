package com.haxos.foodity.ui.main.notes.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.databinding.FragmentNoteContentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteContentFragment: Fragment() {

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

        val toolbar = binding.toolbarActivityMain
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        noteContentViewModel.noteLiveData.observe(viewLifecycleOwner, {
            binding.noteName.text = it.name
            binding.noteDescription.text = it.description
        })

        val noteId: Long? = arguments?.getLong("noteId")
        if (noteId != null) {
            noteContentViewModel.fetchNote(noteId)
        }

        return binding.root
    }
}