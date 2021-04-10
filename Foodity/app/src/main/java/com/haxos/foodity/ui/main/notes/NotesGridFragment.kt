package com.haxos.foodity.ui.main.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.databinding.FragmentNotesGridBinding
import com.haxos.foodity.ui.main.notes.entries.NotesAdapter
import com.haxos.foodity.ui.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesGridFragment: Fragment() {

    companion object {
        fun newInstance(categoryId: Long): NotesGridFragment {
            val args = Bundle()
            args.putLong("categoryId", categoryId)
            val fragment = NotesGridFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: FragmentNotesGridBinding
    @Inject lateinit var notesGridViewModel: NotesGridViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotesGridBinding.inflate(inflater)

        val notesRecyclerView: RecyclerView = binding.recyclerViewNotes
        notesRecyclerView.layoutManager = GridLayoutManager(context, 3)

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

    inner class NoteClickListener : NotesAdapter.INoteClickListener {
        override fun onClick(note: Note) {
            val noteContentFragment = NoteContentFragment.newInstance(note.id)
            val notesFragment = requireParentFragment()
            notesFragment.replace(noteContentFragment)
        }
    }
}