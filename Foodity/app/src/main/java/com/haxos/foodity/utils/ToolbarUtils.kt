package com.haxos.foodity.utils

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun Toolbar.enableBackButton(fragment: Fragment) {
    this.setNavigationOnClickListener {
        val fragmentManager = fragment.parentFragmentManager
        if (fragmentManager.backStackEntryCount == 0) {
            fragment.requireActivity().onBackPressed()
        } else {
            fragmentManager.popBackStack()
        }
    }
}