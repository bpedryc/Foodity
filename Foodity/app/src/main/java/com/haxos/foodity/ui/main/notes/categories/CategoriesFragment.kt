package com.haxos.foodity.ui.main.notes.categories

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.databinding.FragmentCategoriesBinding
import com.haxos.foodity.ui.main.notes.notes.NotesFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingFragment
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import com.haxos.foodity.ui.main.notes.notesearch.NotesSearchingToolbar
import com.haxos.foodity.ui.settings.SettingsActivity
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment : Fragment(), INoteSearchingFragment {

    companion object {
        fun newInstance(profileId: Long): CategoriesFragment {
            val args = Bundle()
            args.putLong("profileId", profileId)
            val fragment = CategoriesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var notesViewModel: NotesViewModel
    lateinit var binding: FragmentCategoriesBinding
    override lateinit var noteSearchRecyclerView: RecyclerView

    private var categoryCreationDialog: AlertDialog? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater)
        noteSearchRecyclerView = binding.recyclerviewSearch

        val toolbar: Toolbar = binding.toolbarActivityMain

        val profileId : Long = arguments?.getLong("profileId")
            ?: notesViewModel.currentProfileId
            ?: return binding.root

        if (!notesViewModel.isCurrentProfile(profileId)) {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        } else {
            toolbar.setNavigationOnClickListener {
                startActivity(Intent(context, SettingsActivity::class.java))
            }
        }

        val notesSearchingToolbar = NotesSearchingToolbar(toolbar, this)

        val categoriesRecyclerView : CategoriesRecyclerView = binding.recyclerviewCategories
        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val categoriesAdapter = CategoriesAdapter(clickListener = CategoryClickListener(profileId))
        categoriesRecyclerView.adapter = categoriesAdapter
        notesViewModel.categoriesLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                binding.backgroundText.visibility = View.VISIBLE
            } else {
                binding.backgroundText.visibility = View.GONE
            }
            categoriesAdapter.setCategories(it)
        })

        if (notesViewModel.isCurrentProfile(profileId)) {
            toolbar.inflateMenu(R.menu.menu_notes_categories)
            toolbar.setOnMenuItemClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
                        .setView(R.layout.dialog_category)
                        .setTitle("New category")
                        .setMessage("Create a category")
                        .setPositiveButton(android.R.string.yes) {_, _ -> createCategory() }
                        .setNegativeButton(android.R.string.no) {_, _ -> }
                categoryCreationDialog = builder.show()
                true
            }

            registerForContextMenu(categoriesRecyclerView)
        }

        notesViewModel.fetchCategories(profileId)

        return binding.root
    }

    private fun createCategory() {
        val editText = categoryCreationDialog?.findViewById<EditText>(R.id.category_name) ?: return
        val name = editText.text.toString()
        notesViewModel.createCategory(name)
    }

    inner class CategoryClickListener(val profileId: Long
    ) : CategoriesAdapter.ICategoryClickListener {
        override fun onClick(category: NotesCategory) {
            val notesGridFragment = NotesFragment.newInstance(category.id, profileId)
            replace(notesGridFragment)
        }
    }

    override val noteSearchingViewModel: INoteSearchingViewModel
        get() = notesViewModel

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater? = activity?.menuInflater
        inflater?.inflate(R.menu.contextmenu_category, menu)
    }

    override fun onContextItemSelected(item: MenuItem) : Boolean {
        val categoryInfo = item.menuInfo as CategoriesRecyclerView.CategoryContextMenuInfo
        if (item.itemId == R.id.action_category_delete) {
            notesViewModel.deleteCategory(categoryInfo.id)
        }
        return super.onContextItemSelected(item)
    }

}
