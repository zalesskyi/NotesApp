package com.zalesskyi.notesapp.presentation.auth

import com.zalesskyi.notesapp.navigation.NavDirections
import com.zalesskyi.notesapp.navigation.NavigationCommand
import com.zalesskyi.notesapp.navigation.Navigator
import com.zalesskyi.notesapp.presentation.auth.signIn.SignInFragmentDirections
import com.zalesskyi.notesapp.presentation.auth.signUp.SignUpFragmentDirections
import javax.inject.Inject
import javax.inject.Named

interface AuthNavigator {

    fun toMain()

    fun toSignUp()

    fun toSignIn()
}

class AuthNavigatorImpl
@Inject
constructor(@Named(APP_NAVIGATOR)
            private val navigator: Navigator,
            @Named(APP_NAV_NAVIGATOR)
            private val appNavigator: Navigator
) : AuthNavigator {

    companion object {
        const val APP_NAVIGATOR = "AuthNavigator.APP_NAVIGATOR"
        const val APP_NAV_NAVIGATOR = "AuthNavigator.APP_NAV_NAVIGATOR"
    }

    override fun toMain() {
        navigator.navigate(NavDirections.AUTH_TO_MAIN)
    }

    override fun toSignUp() {
        appNavigator.navigate(NavigationCommand.ToDirections(
              SignInFragmentDirections.actionSignInFragmentToSignUpFragment()))
    }

    override fun toSignIn() {
        appNavigator.navigate(NavigationCommand.ToDirections(
              SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()))
    }
}