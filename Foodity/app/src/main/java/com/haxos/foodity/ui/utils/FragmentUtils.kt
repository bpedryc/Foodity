package com.haxos.foodity.ui.utils

import android.content.Intent
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.haxos.foodity.ui.settings.SettingsActivity

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

