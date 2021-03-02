package com.zalesskyi.notesapp.presentation.auth

import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import javax.inject.Inject

interface AuthViewModel : BaseViewModel

class AuthViewModelImpl
@Inject
constructor() : BaseViewModelImpl(), AuthViewModel