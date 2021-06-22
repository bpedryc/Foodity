package com.haxos.foodity.ui.main.notes.content

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.PopupMenu
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

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri: Uri ->
        prepareRequestBody(uri)?.let {
            noteViewModel.uploadImage(it)
        }
    }

    private fun prepareRequestBody(uri: Uri) : ContentUriRequestBody? {
        val contentResolver : ContentResolver = activity?.contentResolver ?: return null
        val mimeType : String? = contentResolver.getType(uri)
        val fileExtension : String = mimeType?.split("/")?.last() ?: ""
        val fileName = UUID.randomUUID().toString()
        val fullFileName = "$fileName.$fileExtension"

        return ContentUriRequestBody(fullFileName, contentResolver, uri)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteEditBinding.inflate(inflater)

        val toolbar = binding.toolbarNoteEdit
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        setHasOptionsMenu(true)
        toolbar.setOnMenuItemClickListener(this)

        noteViewModel.imageElementRequest.observe(viewLifecycleOwner, {
            if (it.contentUrl != null) {
//                Toast.makeText(context, "Index: $index; URL: $url", Toast.LENGTH_LONG).show()
                noteViewModel.editImage(it.recyclerItemIndex, it.contentUrl)
            } else {
                getContent.launch("image/*")
            }
        })

        val popupMenu = PopupMenu(requireContext(), binding.actionNoteelementAdd)
        popupMenu.menu.add("Text")
        popupMenu.menu.add("List")
        popupMenu.menu.add("Image")
        binding.actionNoteelementAdd.setOnClickListener {
            popupMenu.show()
        }

        noteViewModel.editable = true
        noteViewModel.noteId = arguments?.getLong("noteId")

        return binding.also {
            it.viewModel = noteViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_note_confirm -> onNoteEditConfirm()
        }
        return true
    }

    private fun onNoteEditConfirm() {
        noteViewModel.noteEditResult.observe(viewLifecycleOwner, {
            requireActivity().onBackPressed()
        })

        noteViewModel.editNote()

        /*noteViewModel.editNote(
            binding.noteName.text.toString(),
            binding.noteDescription.text.toString()
        )*/
    }
}