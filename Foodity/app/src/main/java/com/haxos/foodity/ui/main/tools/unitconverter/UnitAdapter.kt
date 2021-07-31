package com.haxos.foodity.ui.main.tools.unitconverter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.MetricUnit

class UnitAdapter(
        adapterContext: Context,
        private val units: List<MetricUnit>
) : ArrayAdapter<MetricUnit>(adapterContext, 0, units) {

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return createView(position, recycledView, parent)
    }

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val unit = units[position]

        val view: View = recycledView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_generic, parent, false)

        val productName = view.findViewById<TextView>(R.id.name)
        productName.text = context.getString(unit.stringRes)
        return view
    }

}