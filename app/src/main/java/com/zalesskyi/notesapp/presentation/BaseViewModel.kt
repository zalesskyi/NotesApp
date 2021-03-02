package com.zalesskyi.notesapp.presentation

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.base.CompletionBlock
import com.zalesskyi.domain.base.UseCaseCoroutine
import com.zalesskyi.notesapp.android.system.tools.SingleLiveEvent
import kotlinx.coroutines.launch

interface BaseViewModel {

    val progressLiveData: LiveData<Boolean>
}

open class BaseViewModelImpl : ViewModel(), BaseViewModel {

    override val progressLiveData = SingleLiveEvent<Boolean>()

    fun <T, R, U : UseCaseCoroutine<T, R>>U.launch(param: R, block: CompletionBlock<T>) {
        viewModelScope.launch { execute(param, block) }
    }

    @CallSuper
    protected open fun showProgress() {
        progressLiveData.value = true
    }

    @CallSuper
    protected open fun hideProgress() {
        progressLiveData.value = false
    }
}