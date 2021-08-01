package com.haxos.foodity.ui.main.notes.notesearch

import androidx.recyclerview.widget.RecyclerView

interface INoteSearchingFragment {
    var profileId: Long?
    val noteSearchingViewModel: INoteSearchingViewModel
    val noteSearchRecyclerView: RecyclerView
}
