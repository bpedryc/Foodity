package com.haxos.foodity.ui.main.notes

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.retrofit.INotesService
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val currentUserInfo: ICurrentUserInfo,
    private val notesService: INotesService
): ViewModel(), INoteSearchingViewModel {

    private val _categoriesLiveData = MutableLiveData<List<NotesCategory>>()
    val categoriesLiveData: LiveData<List<NotesCategory>> = _categoriesLiveData

    private val _searchLiveData = MutableLiveData<List<Note>>()
    override val searchLiveData: LiveData<List<Note>> = _searchLiveData

    override val searchListener = NoteSearchListener(currentUserInfo, notesService, _searchLiveData)

    init {
        viewModelScope.launch {
            val response = notesService.getCategoriesByUsername(currentUserInfo.user?.username!!)
            _categoriesLiveData.value = response.body()
        }
    }

    class OnCreateCategoryClickListener(
        val dialogFragment: CreateCategoryDialogFragment
    ) : Toolbar.OnMenuItemClickListener {

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            TODO("Not yet implemented")
        }


    }
}