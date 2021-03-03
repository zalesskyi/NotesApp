package com.zalesskyi.notesapp.presentation.splash

import android.os.Bundle
import androidx.lifecycle.observe
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.presentation.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>() {

    override val layoutResId = R.layout.activity_splash

    override val viewModel: SplashViewModel by inject<SplashViewModelImpl>()

    @Inject
    lateinit var navigator: SplashNavigator

    override fun observeViewModel() {
        viewModel.isLoggedInLD.observe(this) {
            navigator.run {
                if (it) toMain() else toAuth()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkLoggedIn()
    }
}