package com.zalesskyi.notesapp.presentation.auth.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zalesskyi.domain.usecases.SignUpUseCase
import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SignUpViewModel: BaseViewModel {

    val signUpLD: LiveData<Boolean>

    fun signUp(email: String, password: String)
}

class SignUpViewModelImpl
@Inject
constructor(private val signUpUseCase: SignUpUseCase): BaseViewModelImpl(), SignUpViewModel {

    override val signUpLD = MutableLiveData<Boolean>()

    override fun signUp(email: String, password: String) {
        viewModelScope.launch {
            signUpUseCase.execute(SignUpUseCase.Params(email, password)) {
                onComplete = {
                    signUpLD.value = it
                }
            }
        }
    }
}