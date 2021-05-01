package com.haxos.foodity.ui.main.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.databinding.FragmentCategoriesGridBinding
import com.haxos.foodity.ui.main.SearchResultAdapter
import com.haxos.foodity.ui.main.notes.categories.CategoriesAdapter
import com.haxos.foodity.ui.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesGridFragment : Fragment() {

    @Inject lateinit var categoriesGridViewModel: CategoriesGridViewModel
    @Inject lateinit var noteSearchingToolbar: NoteSearchingToolbar
    lateinit var binding : FragmentCategoriesGridBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesGridBinding.inflate(inflater)

        /*val toolbar: Toolbar = binding.toolbarActivityMain
        toolbar.inflateMenu(R.menu.menu_notes_categories)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }*/




        binding.toolbarFragmentCategories.bind
        noteSearchingToolbar.baseToolbar

        val toolbarContainer : FrameLayout = binding.toolbarContainer
        binding.toolbarFragmentCategories

        val toolbar = NoteSearchingToolbar(binding.toolbarFragmentCategories, categoriesGridViewModel)

//        val searchView : SearchView = binding.notesSearchView
//        val searchRecyclerView : RecyclerView = binding.recyclerViewSearch
        val notesSearch = NotesSearch()

        searchView.setOnQueryTextListener(NotesSearchListener(lifecycle, categoriesGridViewModel))

        searchRecyclerView.layoutManager = LinearLayoutManager(context)

        val searchAdapter = object : SearchResultAdapter(
            clickListener = NotesFragment.NoteSearchClickListener(this)) {
            override fun getTextToDisplay(objectToDisplay: Any): String {
                return (objectToDisplay as Note).name
            }
        }
        searchRecyclerView.adapter = searchAdapter
        notesViewModel.searchLiveData.observe(viewLifecycleOwner, {
            searchAdapter.setItems(it)
        })

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
        val categoriesRecyclerView : RecyclerView = binding.recyclerViewCategories
        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val categoriesAdapter = CategoriesAdapter(clickListener = CategoryClickListener())
        categoriesRecyclerView.adapter = categoriesAdapter
        categoriesGridViewModel.categoriesLiveData.observe(viewLifecycleOwner, {
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
}