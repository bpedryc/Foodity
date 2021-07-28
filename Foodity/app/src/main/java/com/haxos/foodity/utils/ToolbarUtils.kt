package com.haxos.foodity.utils

import android.app.Activity
import androidx.appcompat.widget.Toolbar


fun Toolbar.enableBackButton(activity: Activity) {
    this.setNavigationOnClickListener {
        activity.onBackPressed()
    }
}