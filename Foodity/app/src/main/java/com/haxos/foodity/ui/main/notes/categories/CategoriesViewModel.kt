package com.haxos.foodity.ui.main.notes.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NotesCategory
import com.haxos.foodity.data.model.NotesCategoryRequest
import com.haxos.foodity.retrofit.INotesCategoriesService
import com.haxos.foodity.retrofit.INotesService
import com.haxos.foodity.ui.main.notes.notesearch.INoteSearchingViewModel
import com.haxos.foodity.ui.main.notes.notesearch.NoteSearchListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val currentUserInfo: ICurrentUserInfo,
    private val notesService: INotesService,
    private val categoriesService: INotesCategoriesService
): ViewModel(), INoteSearchingViewModel {

    val currentProfileId: Long?
        get() = currentUserInfo.profileId

    private val _categoriesLiveData = MutableLiveData<MutableList<NotesCategory>>()
    val categoriesLiveData: LiveData<MutableList<NotesCategory>> = _categoriesLiveData

    private val _searchLiveData = MutableLiveData<List<Note>>()
    override val searchLiveData: LiveData<List<Note>> = _searchLiveData

    override val searchListener = NoteSearchListener(currentUserInfo, notesService, _searchLiveData)

    fun fetchCategories(profileId: Long) = viewModelScope.launch {
        val response = categoriesService.getCategoriesByProfileId(profileId)
        val categories = response.body() ?: return@launch
        _categoriesLiveData.value = categories.toMutableList()
    }

    fun createCategory(name: String) = viewModelScope.launch {
        val request = NotesCategoryRequest(name = name, thumbnail = 0, profileId = currentUserInfo.profileId!!)
        val createdCategory = categoriesService.createCategory(request).body()
        if (createdCategory != null) {
            _categoriesLiveData.value?.add(createdCategory)
            _categoriesLiveData.value = _categoriesLiveData.value
        }
    }

    fun renameCategory(id: Long, newCategoryName: String) = viewModelScope.launch {
        val categoryRequest = NotesCategoryRequest(
            id = id, name = newCategoryName, profileId = currentUserInfo.profileId!!)
        val response = categoriesService.edit(categoryRequest)
        response.body()?.let { renamedCategory ->
            val existingCategoryToRename = _categoriesLiveData.value
                ?.find { it.id == id } ?: return@launch
            existingCategoryToRename.name = renamedCategory.name
            _categoriesLiveData.value = _categoriesLiveData.value
        }
    }

    fun deleteCategory(id: Long) = viewModelScope.launch {
        val response = categoriesService.delete(id)
        if (response.body() == true) {
            _categoriesLiveData.value?.removeIf { id == it.id }
            _categoriesLiveData.value = _categoriesLiveData.value
        }
    }

    fun isCurrentProfile(profileId: Long): Boolean {
        return currentUserInfo.profileId == profileId
    }

    fun isCurrentUserModerator(): Boolean {
        return currentUserInfo.userRoles.any {it.contains("moderator")}
    }
}