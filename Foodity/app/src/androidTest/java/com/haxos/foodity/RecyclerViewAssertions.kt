package com.haxos.foodity

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import junit.framework.Assert
import org.hamcrest.Matcher

object RecyclerViewAssertions {

    fun itemViewMatches(position: Int, viewMatcher: Matcher<View>): ViewAssertion {
        return itemViewMatches(position, -1, viewMatcher)
    }

    fun itemViewMatches(
        position: Int,
        @IdRes resId: Int,
        viewMatcher: Matcher<View>
    ): ViewAssertion {
        Assert.assertNotNull(viewMatcher)

        return ViewAssertion { view, noViewException ->
            if (noViewException != null) {
                throw noViewException
            }

            Assert.assertTrue("View is RecyclerView", view is RecyclerView)

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            val itemType = adapter!!.getItemViewType(position)
            val viewHolder = adapter.createViewHolder(recyclerView, itemType)
            adapter.bindViewHolder(viewHolder, position)

            val targetView = if (resId == -1) {
                viewHolder.itemView
            } else {
                viewHolder.itemView.findViewById(resId)
            }

            if (viewMatcher.matches(targetView)) {
                return@ViewAssertion  // Found a matching view
            }

            Assert.fail("No match found")
        }
    }
}