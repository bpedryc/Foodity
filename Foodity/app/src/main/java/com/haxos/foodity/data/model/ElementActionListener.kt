package com.haxos.foodity.data.model

class ElementActionListener(
        val onMoveUp: (NoteElementViewModel) -> Unit,
        val onMoveDown: (NoteElementViewModel) -> Unit,
        val onDelete: (NoteElementViewModel) -> Unit,
){
   /* private fun getElements() : MutableList<RecyclerItem> {
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
    }*/
}
