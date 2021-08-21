package com.haxos.foodity.ui.main.notes.content

import android.graphics.PorterDuff
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.koushikdutta.ion.Ion

@BindingAdapter("items")
fun setRecyclerViewItems(
    recyclerView: RecyclerView,
    items: List<RecyclerItem>?
) {
    var adapter = (recyclerView.adapter as? RecyclerViewAdapter)
    if (adapter == null) {
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
    }

    adapter.updateData(items.orEmpty())
}

@BindingAdapter("sourcePath")
fun setImageViewResource(
    imageView: ImageView,
    url: String
) {
    Ion.with(imageView)
        .placeholder(android.R.drawable.stat_sys_upload)
        .error(android.R.drawable.stat_sys_upload)
        .load(url)
}

@BindingAdapter("tint")
fun setImageTint(
    imageButton: ImageButton,
    @ColorInt color: Int
) {
    imageButton.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}
