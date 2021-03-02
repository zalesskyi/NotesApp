package com.zalesskyi.notesapp.presentation.splash

import com.zalesskyi.notesapp.navigation.NavDirections
import com.zalesskyi.notesapp.navigation.Navigator
import javax.inject.Inject
import javax.inject.Named

interface SplashNavigator {

    fun toMain()

    fun toAuth()
}

class SplashNavigatorImpl
@Inject
constructor(@Named(APP_NAVIGATOR)
            private val navigator: Navigator
) : SplashNavigator {

    companion object {

        const val APP_NAVIGATOR = "SplashNavigator.APP_NAVIGATOR"
    }

    override fun toMain() {
        navigator.navigate(NavDirections.SPLASH_TO_MAIN)
    }

    override fun toAuth() {
        navigator.navigate(NavDirections.SPLASH_TO_AUTH)
    }
}