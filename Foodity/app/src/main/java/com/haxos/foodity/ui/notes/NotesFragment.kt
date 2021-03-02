package com.haxos.foodity.ui.notes

import android.app.ActionBar
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNotesBinding
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

        val textView: TextView = binding.textHome
        notesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
        //val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        /*(menu.findItem(R.id.search).actionView as SearchView).apply {
            //setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false // Do not iconify the widget; expand it by default
            setBackgroundColor(ContextCompat.getColor(context, R.color.grey))
        }*/

        super.onCreateOptionsMenu(menu, inflater)
    }
}
