package com.haxos.foodity.ui.main.notes.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NotesCategory

class CategoriesAdapter (
    private var categories: ArrayList<NotesCategory> = ArrayList()
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView) {
        val categoryName: TextView = categoryView.findViewById(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_view_category, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryName.text = categories[position].name
    }

    override fun getItemCount(): Int = categories.size

    fun setCategories(categories: List<NotesCategory>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }


}