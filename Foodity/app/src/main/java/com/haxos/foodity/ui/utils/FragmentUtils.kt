package com.haxos.foodity.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Fragment.replace(targetFragment: Fragment) {
    activity?.hideKeyboard()

    val fragmentManager = parentFragmentManager
    fragmentManager.beginTransaction()
    fragmentManager.commit {
        replace(id, targetFragment)
        setReorderingAllowed(true)
        addToBackStack(null)
    }
}

