package com.haxos.foodity.ui.main.notes.content

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R

class ImageElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.element_image_title)
    val image: ImageView = itemView.findViewById(R.id.element_image_image)
}