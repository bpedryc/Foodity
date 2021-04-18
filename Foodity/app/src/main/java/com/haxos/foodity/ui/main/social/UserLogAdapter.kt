package com.haxos.foodity.ui.main.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.NoteLog

class UserLogAdapter(
    private var displayableLogs: List<DisplayableLog> = ArrayList()
) : RecyclerView.Adapter<UserLogAdapter.ViewHolder>() {

    inner class ViewHolder(logView: View) : RecyclerView.ViewHolder(logView) {
        val info: TextView = logView.findViewById(R.id.textview_log_info)
        val timestamp: TextView = logView.findViewById(R.id.textview_log_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_log, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val log = displayableLogs[position]
        holder.info.text = log.info
        holder.timestamp.text = log.timestamp
    }

    override fun getItemCount(): Int = displayableLogs.size

    fun setLogs(logs: List<DisplayableLog>) {
        displayableLogs = logs
        notifyDataSetChanged()
    }
}
