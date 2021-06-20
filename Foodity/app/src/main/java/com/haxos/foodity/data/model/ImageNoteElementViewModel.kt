package com.haxos.foodity.data.model

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.startActivityForResult
import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem
import com.haxos.foodity.utils.scanForActivity
import com.haxos.foodity.utils.unwrap

class ImageNoteElementViewModel (
        val imageElement: ImageNoteElement,
        elementActionListener: ElementActionListener
) : NoteElementViewModel(elementActionListener) {

    override fun toRecyclerItem(editable: Boolean): RecyclerItem {
        var layout = R.layout.recyclerview_element_image
        if (editable) {
            layout = R.layout.recyclerview_element_image_edit
        }
        return RecyclerItem(
                data = this,
                variableId = BR.viewModel,
                layoutId = layout
        )
    }

    fun editImage(imageView: View) {
        val activity = imageView.context.scanForActivity()
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity?.startActivityForResult(intent, 1)
    }
}