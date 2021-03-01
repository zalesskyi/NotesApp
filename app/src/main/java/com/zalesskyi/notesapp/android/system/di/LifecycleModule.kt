package com.zalesskyi.notesapp.android.system.di

import androidx.lifecycle.LifecycleObserver
import com.zalesskyi.notesapp.android.system.lifecycle.Lifecycle
import com.zalesskyi.notesapp.android.system.lifecycle.LifecycleProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LifecycleModule.LifecycleBinds::class])
class LifecycleModule {

    @Provides
    @Singleton
    fun provideLifecycleProvider() = LifecycleProvider()

    @Module
    interface LifecycleBinds {

        @Binds
        fun bindAppLifecycle(lifecycleProvider: LifecycleProvider): Lifecycle

        @Binds
        fun bindAppLifecycleOwner(lifecycleProvider: LifecycleProvider): LifecycleObserver
    }
}
