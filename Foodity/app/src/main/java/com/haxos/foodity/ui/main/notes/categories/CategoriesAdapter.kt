package com.haxos.foodity.ui.main.notes.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NotesCategory

class CategoriesAdapter (
    private var categories: ArrayList<NotesCategory> = ArrayList(),
    private var clickListener: ICategoryClickListener
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    interface ICategoryClickListener {
        fun onClick(category: NotesCategory)
    }

    inner class ViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView){
        val categoryName: TextView = categoryView.findViewById(R.id.category_name)
        init {
            categoryView.isLongClickable = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.gridview_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name
        holder.itemView.setOnClickListener { clickListener.onClick(category) }
    }

    override fun getItemId(position: Int): Long {
        return categories[position].id
    }

    override fun getItemCount(): Int = categories.size

    fun setCategories(categories: List<NotesCategory>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }
}