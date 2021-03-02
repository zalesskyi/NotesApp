package com.zalesskyi.notesapp.presentation.splash

import android.os.Bundle
import android.os.Handler
import com.zalesskyi.notesapp.R
import com.zalesskyi.notesapp.presentation.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {

        private const val FINISH_PERCENT = 0.6F
    }

    override val layoutResId = R.layout.activity_splash

    override val viewModel: SplashViewModel by inject<SplashViewModelImpl>()

    @Inject
    lateinit var navigator: SplashNavigator

    override fun observeViewModel() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*lawSplash?.addAnimatorUpdateListener { animator ->
            if ((animator.animatedValue as Float) > FINISH_PERCENT) {
                lawSplash?.cancelAnimation()
                finishSplash()
            }
        }*/
        finishSplash()
    }

    private fun finishSplash() {
        Handler().postDelayed({
            navigator.toMain()
            finish()
        }, 2000)
    }
}