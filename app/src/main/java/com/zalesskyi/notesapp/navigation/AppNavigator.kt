package com.zalesskyi.notesapp.navigation

import com.stromee.navigation.screen.ActivityRequestScreen
import com.stromee.navigation.screen.AppScreen
import com.zalesskyi.notesapp.android.system.ContextHolder
import com.zalesskyi.notesapp.navigation.NavDirections.AUTH_TO_MAIN
import com.zalesskyi.notesapp.navigation.NavDirections.SPLASH_TO_AUTH
import com.zalesskyi.notesapp.navigation.NavDirections.SPLASH_TO_MAIN
import com.zalesskyi.notesapp.presentation.auth.AuthActivity
import com.zalesskyi.notesapp.presentation.main.MainActivity
import javax.inject.Inject

class AppNavigator @Inject constructor(
    private val contextHolder: ContextHolder
) : Navigator {

    override fun navigate(command: NavigationCommand) = Unit

    override fun navigate(screen: AppScreen) {
        realNavigation(screen)
    }

    private fun realNavigation(screen: AppScreen) {
        when (screen) {
            is ActivityRequestScreen -> {
                contextHolder.getContext()?.run {
                    startActivityForResult(
                        screen.getActivityIntent(this), screen.requestCode
                    )
                }
            }
            else -> Unit
        }
    }

    override fun navigate(direction: String, args: Map<*, *>?) {
        realNavigation(direction, args)
    }

    private fun realNavigation(direction: String, args: Map<*, *>?) {
        contextHolder.getContext()?.let { context ->
            val intent = when (direction) {
                SPLASH_TO_MAIN, AUTH_TO_MAIN -> MainActivity.getIntent(context)
                SPLASH_TO_AUTH -> AuthActivity.getIntent(context)
                else -> throw NotImplementedError()
            }
            context.startActivity(intent)
        }
    }
}