package com.haxos.foodity.ui.main.notes.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.databinding.FragmentNotesBinding
import com.haxos.foodity.ui.main.notes.notes.NotesGridFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import com.haxos.foodity.ui.main.notes.notesearch.NotesSearchingToolbar
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment(), INoteSearchingFragment {

    @Inject lateinit var notesViewModel: NotesViewModel
    lateinit var binding: FragmentNotesBinding
    override lateinit var noteSearchRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater)
        noteSearchRecyclerView = binding.recyclerviewSearch

        val toolbar: Toolbar = binding.toolbarActivityMain
        toolbar.inflateMenu(R.menu.menu_notes_categories)
        toolbar.setOnMenuItemClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            builder.setView(R.layout.dialog_note_category)
                .setTitle("New category")
                .setMessage("Create a category")
                .setPositiveButton(android.R.string.yes) {_, _ -> }
                .setNegativeButton(android.R.string.no) {_, _ -> }
                .show()
            true
        }
        val notesSearchingToolbar = NotesSearchingToolbar(toolbar, this)

        val categoriesRecyclerView : RecyclerView = binding.recyclerviewCategories
        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val categoriesAdapter = CategoriesAdapter(clickListener = CategoryClickListener())
        categoriesRecyclerView.adapter = categoriesAdapter
        notesViewModel.categoriesLiveData.observe(viewLifecycleOwner, {
            categoriesAdapter.setCategories(it)
        })

        return binding.root
    }

    inner class CategoryClickListener : CategoriesAdapter.ICategoryClickListener {
        override fun onClick(category: NotesCategory) {
            val notesGridFragment = NotesGridFragment.newInstance(category.id)
            replace(notesGridFragment)
        }
    }

    override val noteSearchingViewModel: INoteSearchingViewModel
        get() = notesViewModel

}


        /*if (childFragmentManager.fragments.size == 0) {
            childFragmentManager.commit {
                setReorderingAllowed(true)
                add<CategoriesGridFragment>(binding.fragmentCategories.id)
            }
        }

        val textView: TextView = binding.textHome
        notesViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })*/