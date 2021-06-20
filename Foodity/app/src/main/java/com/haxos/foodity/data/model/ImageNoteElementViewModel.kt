package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ImageNoteElementViewModel (
        val imageElement: ImageNoteElement,
        elementActionListener: ElementActionListener,
        val onEditImage: (ImageNoteElement) -> Unit
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

    fun editImage() {
        onEditImage(imageElement)
    }
}
   /* fun editImage(imageView: View) {
        val activity = imageView.context.scanForActivity()
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.last()
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity?.startActivityForResult(intent, 1)
    }*/