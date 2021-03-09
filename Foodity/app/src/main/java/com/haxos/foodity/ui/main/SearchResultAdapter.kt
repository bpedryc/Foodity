package com.haxos.foodity.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.User
import org.w3c.dom.Text

class SearchResultAdapter(
        private var profileList: ArrayList<Profile> = ArrayList()
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    inner class ViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val username: TextView = userView.findViewById(R.id.username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_view_users, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = profileList[position].username
    }

    override fun getItemCount(): Int = profileList.size

    fun setProfiles(profiles: List<Profile>) {
        profileList.clear()
        profileList.addAll(profiles)
        notifyDataSetChanged()
    }



    /*override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        if (convertView == null) {
            viewHolder = ViewHolder(convertView)
        }
        var view = inflater.inflate(R.layout.list_view_users, parent, false)
        var usernameView = view.findViewById<TextView>(R.id.name)
        usernameView.text = userList[position].username

        return view
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return userList[position].id
    }

    override fun getCount(): Int {
        return userList.size
    }*/
}