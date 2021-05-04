package com.haxos.foodity.ui.main.notes

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.haxos.foodity.databinding.DialogNoteCategoryBinding

class CreateCategoryDialogFragment : DialogFragment() {

    private lateinit var binding: DialogNoteCategoryBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNoteCategoryBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setTitle("New category")
            .setMessage("Create a category")
            .setPositiveButton(android.R.string.yes) { dialog, dialogId ->
                val categoryName = binding.categoryName
            }
            .setNegativeButton(android.R.string.no) {_, _ -> }
            .create()
    }

    companion object {
        const val TAG = "CreateCategoryDialog"
    }
}