package com.zalesskyi.notesapp.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.usecases.IsLoggedInUseCase
import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SplashViewModel : BaseViewModel {

    val isLoggedInLD: LiveData<Boolean>

    fun checkLoggedIn()
}

class SplashViewModelImpl
@Inject
constructor(private val isLoggedInUseCase: IsLoggedInUseCase) : BaseViewModelImpl(), SplashViewModel {

    override val isLoggedInLD = MutableLiveData<Boolean>()

    override fun checkLoggedIn() {
        viewModelScope.launch {
            isLoggedInUseCase.execute(Unit) {
                onComplete = {
                    isLoggedInLD.value = it
                }
            }
        }
    }
}