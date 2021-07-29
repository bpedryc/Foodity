package com.haxos.foodity.ui.main.tools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.ConverterProduct

class ProductsAdapter(
        adapterContext: Context,
        private val products: List<ConverterProduct>
) : ArrayAdapter<ConverterProduct>(adapterContext, 0, products) {

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return createView(position, recycledView, parent)
    }

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val product = products[position]

        val view: View = recycledView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_product, parent, false)

        val productName = view.findViewById<TextView>(R.id.product_name)
        productName.text = context.getString(product.name)
        return view
    }
}