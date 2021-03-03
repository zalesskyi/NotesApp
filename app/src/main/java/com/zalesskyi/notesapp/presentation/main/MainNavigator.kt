package com.zalesskyi.notesapp.presentation.main

import com.zalesskyi.notesapp.navigation.NavigationCommand
import com.zalesskyi.notesapp.navigation.Navigator
import com.zalesskyi.notesapp.presentation.main.list.ListFragmentDirections
import javax.inject.Inject
import javax.inject.Named

interface MainNavigator {

    fun toCreateNewNote()
}

class MainNavigatorImpl
@Inject
constructor(@Named(APP_NAV_NAVIGATOR)
            private val navigator: Navigator) : MainNavigator {

    companion object {
        const val APP_NAVIGATOR = "MainNavigator.APP_NAVIGATOR"
        const val APP_NAV_NAVIGATOR = "MainNavigator.APP_NAV_NAVIGATOR"
    }

    override fun toCreateNewNote() {
        navigator.navigate(
            NavigationCommand.ToDirections(
            ListFragmentDirections.actionListFragmentToCreateNotFragment()))
    }
}