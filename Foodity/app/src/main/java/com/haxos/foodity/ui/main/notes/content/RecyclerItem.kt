package com.haxos.foodity.ui.main.notes.content

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

data class RecyclerItem (
   val data: Any,
   @LayoutRes val layoutId: Int,
   val variableId: Int
) {
    fun bind(binding: ViewDataBinding) {
        binding.setVariable(variableId, data)
    }
}