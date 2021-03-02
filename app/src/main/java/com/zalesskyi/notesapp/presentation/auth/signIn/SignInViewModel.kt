package com.zalesskyi.notesapp.presentation.auth.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.usecases.SignInUseCase
import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SignInViewModel: BaseViewModel {

    val signInLD: LiveData<Boolean>

    fun signIn(email: String, password: String)
}

class SignInViewModelImpl
@Inject
constructor(private val signInUseCase: SignInUseCase): BaseViewModelImpl(), SignInViewModel {

    override val signInLD = MutableLiveData<Boolean>()

    override fun signIn(email: String, password: String) {
        viewModelScope.launch {
            signInUseCase.execute(SignInUseCase.Params(email, password)) {
                onComplete = {
                    signInLD.value = it
                }
            }
        }
    }
}