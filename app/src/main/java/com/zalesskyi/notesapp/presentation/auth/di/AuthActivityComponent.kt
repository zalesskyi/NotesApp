package com.zalesskyi.notesapp.presentation.auth.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.zalesskyi.notesapp.android.di.scopes.PerActivity
import com.zalesskyi.notesapp.android.di.viewmodelfactory.ViewModelKey
import com.zalesskyi.notesapp.android.system.ContextHolder
import com.zalesskyi.notesapp.navigation.AppNavComponentsNavigator
import com.zalesskyi.notesapp.navigation.AppNavProvider
import com.zalesskyi.notesapp.navigation.AppNavigator
import com.zalesskyi.notesapp.navigation.Navigator
import com.zalesskyi.notesapp.presentation.auth.AuthActivity
import com.zalesskyi.notesapp.presentation.auth.AuthNavigator
import com.zalesskyi.notesapp.presentation.auth.AuthNavigatorImpl
import com.zalesskyi.notesapp.presentation.auth.AuthViewModelImpl
import com.zalesskyi.notesapp.presentation.auth.signIn.SignInFragment
import com.zalesskyi.notesapp.presentation.auth.signIn.di.SignInFragmentComponent
import com.zalesskyi.notesapp.presentation.auth.signUp.SignUpFragment
import com.zalesskyi.notesapp.presentation.auth.signUp.di.SignUpFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Named

@PerActivity
@Subcomponent(
        modules = [
            (AuthActivityComponent.ActivityBindsModule::class),
            (AuthActivityComponent.FragmentBindingsModule::class),
            (AuthActivityComponent.ActivityModule::class),
            (AuthActivityComponent.BindingsModule::class)
        ]
)
interface AuthActivityComponent : AndroidInjector<AuthActivity> {
    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<AuthActivity>

    @Module
    interface ActivityBindsModule {

        @Binds
        @IntoMap
        @ViewModelKey(AuthViewModelImpl::class)
        fun bindAuthViewModel(viewModel: AuthViewModelImpl): ViewModel

        @Binds
        fun provideActivityModule(activity: AuthActivity): AppCompatActivity

        @Binds
        fun bindAppHostProvider(activity: AuthActivity): AppNavProvider
    }


    @Module
    class ActivityModule {
        @Provides
        @Named(AuthNavigatorImpl.APP_NAVIGATOR)
        fun provideNavController(holder: ContextHolder): Navigator {
            return AppNavigator(holder)
        }

        @Provides
        @Named(AuthNavigatorImpl.APP_NAV_NAVIGATOR)
        fun provideAppNavController(provider: AppNavProvider,
                                    holder: ContextHolder
        ): Navigator {
            return AppNavComponentsNavigator(
                  provider.getNavController()
            ) { provider.finish() }
        }

        @Provides
        fun provideNavigator(@Named(AuthNavigatorImpl.APP_NAVIGATOR) navigator: Navigator,
                             @Named(AuthNavigatorImpl.APP_NAV_NAVIGATOR) appNavigator: Navigator): AuthNavigator =
                AuthNavigatorImpl(navigator, appNavigator)
    }

    @Module(
            subcomponents = [
            ]
    )
    interface BindingsModule

    @Module(
          subcomponents = [
              (SignInFragmentComponent::class),
              (SignUpFragmentComponent::class)
          ]
    )
    abstract class FragmentBindingsModule {
        @Binds
        @IntoMap
        @ClassKey(value = SignInFragment::class)
        internal abstract fun bindSignInFragment(factory: SignInFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(value = SignUpFragment::class)
        internal abstract fun bindSignUpFragment(factory: SignUpFragmentComponent.Factory): AndroidInjector.Factory<*>

    }
}