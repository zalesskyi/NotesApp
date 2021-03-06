package com.zalesskyi.notesapp

import android.app.Application
import com.zalesskyi.notesapp.android.di.component.app.DaggerApplicationComponent
import com.zalesskyi.notesapp.android.di.module.ApplicationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .create(this)
            .inject(this)
    }
}