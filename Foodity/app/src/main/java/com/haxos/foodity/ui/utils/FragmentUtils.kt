package com.haxos.foodity.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Fragment.replace(targetFragment: Fragment) {
    replace(id, targetFragment)
}

fun Fragment.replace(fromFragmentId: Int, targetFragment: Fragment) {
    activity?.hideKeyboard()

    val fragmentManager = parentFragmentManager
    fragmentManager.beginTransaction()
    fragmentManager.commit {
        replace(fromFragmentId, targetFragment)
        setReorderingAllowed(true)
        addToBackStack(null)
    }
}

