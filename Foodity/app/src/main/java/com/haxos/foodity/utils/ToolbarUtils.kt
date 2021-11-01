package com.haxos.foodity.utils

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity

fun Toolbar.enableBackButton(activity: FragmentActivity) {
    this.setNavigationOnClickListener {
        activity.onBackPressed()
    }
}