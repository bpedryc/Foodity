package com.haxos.foodity.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R

abstract class SearchResultAdapter(
    private var items: ArrayList<Any> = ArrayList(),
    protected var clickListener: IItemClickListener? = null
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    interface IItemClickListener {
        fun onItemClick(item: Any)
    }

    inner class ViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val itemText: TextView = userView.findViewById(R.id.text_search_item)
    }

    abstract fun getTextToDisplay(objectToDisplay: Any) : String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listview_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objectAtPosition = items[position]
        holder.itemText.text = getTextToDisplay(objectAtPosition)
        holder.itemView.setOnClickListener { clickListener?.onItemClick(objectAtPosition) }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(objects: List<Any>) {
        items.clear()
        items.addAll(objects)
        notifyDataSetChanged()
    }
}