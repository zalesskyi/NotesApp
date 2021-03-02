package com.zalesskyi.notesapp.android.di.component.app

import com.zalesskyi.notesapp.presentation.auth.AuthActivity
import com.zalesskyi.notesapp.presentation.auth.di.AuthActivityComponent
import com.zalesskyi.notesapp.presentation.main.MainActivity
import com.zalesskyi.notesapp.presentation.main.di.MainActivityComponent
import com.zalesskyi.notesapp.presentation.splash.SplashActivity
import com.zalesskyi.notesapp.presentation.splash.di.SplashActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = [
        (MainActivityComponent::class), (SplashActivityComponent::class), (AuthActivityComponent::class)
    ]
)
abstract class ActivitiesBindsModule {

    @Binds
    @IntoMap
    @ClassKey(SplashActivity::class)
    internal abstract fun bindSplashActivityInjectorFactory(factory: SplashActivityComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindMainActivityInjectorFactory(factory: MainActivityComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(AuthActivity::class)
    internal abstract fun bindAuthActivityInjectorFactory(factory: AuthActivityComponent.Factory): AndroidInjector.Factory<*>

}
