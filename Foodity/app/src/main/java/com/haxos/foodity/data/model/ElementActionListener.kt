package com.haxos.foodity.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ElementActionListener (
        private val elementsLiveData: MutableLiveData<MutableList<RecyclerItem>>
){
    private fun getElements() : MutableList<RecyclerItem> {
        return elementsLiveData.value!!
    }
    private fun getIndex(elementViewModel: NoteElementViewModel) : Int {
        return getElements().indexOfFirst { it.data == elementViewModel }
    }

    fun moveUp(elementViewModel: NoteElementViewModel) {
        val index = getIndex(elementViewModel)
        if (index <= 1) {
            return
        }

        val elements = getElements()
        val prevElement = elements[index - 1]
        elements[index - 1] = elements[index]
        elements[index] = prevElement
        elementsLiveData.value = elementsLiveData.value
    }

    fun moveDown(elementViewModel: NoteElementViewModel) {
        val index = getIndex(elementViewModel)
        val elements = getElements()
        if (index >= elements.size - 1) {
            return
        }

        val nextElement = elements[index + 1]
        elements[index + 1] = elements[index]
        elements[index] = nextElement
        elementsLiveData.value = elementsLiveData.value
    }

    fun delete(elementViewModel: NoteElementViewModel) {
        val index = getIndex(elementViewModel)
        val elements = getElements()
        elements.removeAt(index)
        elementsLiveData.value = elementsLiveData.value
    }
}
