package com.haxos.foodity.ui.main.notes.categories

import android.content.Context
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CategoriesRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var contextMenuInfo: CategoryContextMenuInfo? = null

    override fun getContextMenuInfo(): ContextMenu.ContextMenuInfo {
        return contextMenuInfo ?: super.getContextMenuInfo()
    }

    override fun showContextMenuForChild(originalView: View): Boolean {
        val longPressPosition = getChildAdapterPosition(originalView)
        val longPressId : Long? = adapter?.getItemId(longPressPosition)
        if (longPressPosition < 0 || longPressId == null) {
            return false
        }
        contextMenuInfo = CategoryContextMenuInfo(longPressPosition, longPressId)
        return super.showContextMenuForChild(originalView)
    }

    class CategoryContextMenuInfo(val position: Int, val id: Long) : ContextMenu.ContextMenuInfo
}
