package com.haxos.foodity.ui.main.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.databinding.FragmentCategoriesGridBinding
import com.haxos.foodity.ui.main.notes.categories.CategoriesAdapter
import com.haxos.foodity.ui.main.social.SocialFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesGridFragment : Fragment() {

    @Inject lateinit var categoriesGridViewModel: CategoriesGridViewModel
    lateinit var binding : FragmentCategoriesGridBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesGridBinding.inflate(inflater)

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
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
            fragmentManager.commit {
                val notesGridFragment = NotesGridFragment.newInstance(category.id)
                replace(id, notesGridFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}