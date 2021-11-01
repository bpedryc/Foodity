package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ImageNoteElementViewModel (
        val imageElement: ImageNoteElement,
        elementActionListener: ElementActionListener,
        val onEditImage: (ImageNoteElementViewModel) -> Unit
) : NoteElementViewModel(imageElement, elementActionListener) {

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

    fun editImage() {
        onEditImage(this)
    }
}