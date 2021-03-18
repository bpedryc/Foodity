package com.haxos.foodity.ui.main.notes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.databinding.FragmentNotesBinding
import com.haxos.foodity.ui.main.SearchResultAdapter
import com.haxos.foodity.ui.main.notes.categories.CategoriesAdapter
import com.haxos.foodity.ui.main.social.SocialFragment
import com.haxos.foodity.ui.settings.SettingsActivity
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
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }

        val searchView = binding.notesSearchView
        searchView.setOnQueryTextListener(notesViewModel.searchListener)

        val searchRecyclerView : RecyclerView = binding.recyclerViewSearch
        searchRecyclerView.layoutManager = LinearLayoutManager(context)

        val categoriesRecyclerView : RecyclerView = binding.recyclerViewCategories
        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 2)


        val searchAdapter = object : SearchResultAdapter() {
            override fun getTextToDisplay(objectToDisplay: Any): String {
                return (objectToDisplay as Note).name
            }
        }
        searchRecyclerView.adapter = searchAdapter
        notesViewModel.searchLiveData.observe(viewLifecycleOwner, {
            searchAdapter.setItems(it)
        })

        val categoriesAdapter = CategoriesAdapter(
            clickListener = object : CategoriesAdapter.ICategoryClickListener {
                override fun onClick(category: NotesCategory) {
                    val fragmentManager = parentFragmentManager
                    fragmentManager.beginTransaction()
                    fragmentManager.commit {
                        val socialFragment = SocialFragment()
                        replace(R.id.nav_host_fragment, socialFragment)
                        setReorderingAllowed(true)
                        addToBackStack(null)
                    }
                }
        })
        categoriesRecyclerView.adapter = categoriesAdapter
        notesViewModel.categoriesLiveData.observe(viewLifecycleOwner, {
            categoriesAdapter.setCategories(it)
        })


//        val textView: TextView = binding.textHome
        /*notesViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })*/

        return binding.root
    }
}
