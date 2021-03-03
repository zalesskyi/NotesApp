package com.zalesskyi.notesapp.presentation.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.models.Note
import com.zalesskyi.domain.usecases.GetNotesUseCase
import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ListViewModel: BaseViewModel {

    val notesLD: LiveData<List<Note>>
}

class ListViewModelImpl
@Inject
constructor(private val getNotesUseCase: GetNotesUseCase): BaseViewModelImpl(), ListViewModel {

    override val notesLD = MutableLiveData<List<Note>>()

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotesUseCase.execute(Unit).collect {
                notesLD.postValue(it)
            }
        }
    }
}