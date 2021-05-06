package com.haxos.foodity.ui.main.notes

import androidx.recyclerview.widget.RecyclerView

interface INoteSearchingFragment {
    val noteSearchingViewModel: INoteSearchingViewModel
    val noteSearchRecyclerView: RecyclerView
}
