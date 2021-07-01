package com.haxos.foodity.ui.main.notes.content

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentNoteEditBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class NoteEditFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance(noteId: Long): NoteEditFragment {
            val args = Bundle()
            args.putLong("noteId", noteId)
            val fragment = NoteEditFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding : FragmentNoteEditBinding
    @Inject lateinit var noteViewModel : NoteViewModel

    var selectedNewElement : Int = 0

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri ->
        prepareRequestBody(uri)?.let {
            noteViewModel.uploadImage(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteEditBinding.inflate(inflater)

        val toolbar = binding.toolbarNoteEdit
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        setHasOptionsMenu(true)
        toolbar.setOnMenuItemClickListener(this)

        noteViewModel.editable = true

        val noteId = arguments?.getLong("noteId")
        noteId?.let {
            noteViewModel.fetchNote(it)
        }

        noteViewModel.imageElementRequest.observe(viewLifecycleOwner, {
            if (it.contentUrl != null) {
                noteViewModel.editImage(it.recyclerItemIndex, it.contentUrl)
            } else {
                getContent.launch("image/*")
            }
        })

        return binding.also {
            it.viewModel = noteViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_confirm -> onNoteEditConfirm()
            R.id.action_note_addelement -> onElementAdd()
        }
        return true
    }

    private fun onNoteEditConfirm() {
        noteViewModel.noteEditResult.observe(viewLifecycleOwner, {
            requireActivity().onBackPressed()
        })

        noteViewModel.editNote()
    }

    private fun onElementAdd() {
        AlertDialog.Builder(requireActivity())
            .setTitle("Create new element")
            .setSingleChoiceItems(arrayOf("Text", "List", "Image"), 0) {
                    _, checked  -> selectedNewElement = checked }
            .setPositiveButton(android.R.string.yes) {_, _ -> noteViewModel.addElement(selectedNewElement) }
            .setNegativeButton(android.R.string.no) {_, _ -> }
            .create()
            .show()
    }

    private fun prepareRequestBody(uri: Uri) : ContentUriRequestBody? {
        val contentResolver : ContentResolver = activity?.contentResolver ?: return null
        val mimeType : String? = contentResolver.getType(uri)
        val fileExtension : String = mimeType?.split("/")?.last() ?: ""
        val fileName = UUID.randomUUID().toString()
        val fullFileName = "$fileName.$fileExtension"

        return ContentUriRequestBody(fullFileName, contentResolver, uri)
    }
}