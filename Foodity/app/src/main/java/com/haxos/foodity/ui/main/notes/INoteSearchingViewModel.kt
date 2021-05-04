package com.haxos.foodity.ui.main.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import com.haxos.foodity.data.model.Note

interface INoteSearchingViewModel {
    val searchListener: SearchView.OnQueryTextListener
    val searchLiveData: LiveData<List<Note>>
}
