package com.zalesskyi.notesapp.presentation.splash

import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import javax.inject.Inject

interface SplashViewModel : BaseViewModel

class SplashViewModelImpl
@Inject
constructor() : BaseViewModelImpl(), SplashViewModel