package com.haxos.foodity.ui.main.notes

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.databinding.FragmentNotesBinding
import com.haxos.foodity.ui.main.SearchResultAdapter
import com.haxos.foodity.ui.settings.SettingsActivity
import com.haxos.foodity.ui.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesFragment : Fragment() {

    @Inject lateinit var notesViewModel: NotesViewModel
    private lateinit var binding: FragmentNotesBinding


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater)

        val toolbar: Toolbar = binding.toolbarActivityMain
        toolbar.inflateMenu(R.menu.menu_notes_categories)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }
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

        val searchView = binding.notesSearchView
        searchView.setOnQueryTextListener(notesViewModel.searchListener)

        val searchRecyclerView : RecyclerView = binding.recyclerViewSearch
        searchRecyclerView.layoutManager = LinearLayoutManager(context)

        val searchAdapter = object : SearchResultAdapter(clickListener = NoteSearchClickListener()) {
            override fun getTextToDisplay(objectToDisplay: Any): String {
                return (objectToDisplay as Note).name
            }
        }
        searchRecyclerView.adapter = searchAdapter
        notesViewModel.searchLiveData.observe(viewLifecycleOwner, {
            searchAdapter.setItems(it)
        })

        if (childFragmentManager.fragments.size == 0) {
            childFragmentManager.commit {
                setReorderingAllowed(true)
                add<CategoriesGridFragment>(binding.fragmentCategories.id)
            }
        }

//        val textView: TextView = binding.textHome
        /*notesViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })*/

        return binding.root
    }

    inner class NoteSearchClickListener : SearchResultAdapter.IItemClickListener {
        override fun onItemClick(item: Any) {
            val note = item as Note
            replace(NoteContentFragment.newInstance(note.id))
        }
    }


}
