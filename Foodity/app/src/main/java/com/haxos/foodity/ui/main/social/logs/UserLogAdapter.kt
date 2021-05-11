package com.haxos.foodity.ui.main.social.logs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.R

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
        holder.info.text = HtmlCompat.fromHtml(log.info, HtmlCompat.FROM_HTML_MODE_COMPACT)
        holder.timestamp.text = log.timestamp
    }

    override fun getItemCount(): Int = displayableLogs.size

    fun setLogs(logs: List<DisplayableLog>) {
        displayableLogs = logs
        notifyDataSetChanged()
    }
}
