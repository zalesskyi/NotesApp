package com.zalesskyi.notesapp.presentation.main

import com.zalesskyi.notesapp.presentation.BaseViewModel
import com.zalesskyi.notesapp.presentation.BaseViewModelImpl
import javax.inject.Inject

interface MainViewModel : BaseViewModel

class MainViewModelImpl
@Inject
constructor() : BaseViewModelImpl(), MainViewModel