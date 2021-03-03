package com.zalesskyi.notesapp.presentation.main.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.zalesskyi.notesapp.android.di.scopes.PerActivity
import com.zalesskyi.notesapp.android.di.viewmodelfactory.ViewModelKey
import com.zalesskyi.notesapp.android.system.ContextHolder
import com.zalesskyi.notesapp.navigation.AppNavComponentsNavigator
import com.zalesskyi.notesapp.navigation.AppNavProvider
import com.zalesskyi.notesapp.navigation.AppNavigator
import com.zalesskyi.notesapp.navigation.Navigator
import com.zalesskyi.notesapp.presentation.main.MainActivity
import com.zalesskyi.notesapp.presentation.main.MainNavigator
import com.zalesskyi.notesapp.presentation.main.MainNavigatorImpl
import com.zalesskyi.notesapp.presentation.main.MainViewModelImpl
import com.zalesskyi.notesapp.presentation.main.create.CreateNoteFragment
import com.zalesskyi.notesapp.presentation.main.create.di.CreateNoteFragmentComponent
import com.zalesskyi.notesapp.presentation.main.list.ListFragment
import com.zalesskyi.notesapp.presentation.main.list.di.ListFragmentComponent
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
        (MainActivityComponent.ActivityBindsModule::class),
        (MainActivityComponent.FragmentBindingsModule::class),
        (MainActivityComponent.ActivityModule::class),
        (MainActivityComponent.BindingsModule::class)
    ]
)
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MainActivity>

    @Module
    interface ActivityBindsModule {

        @Binds
        @IntoMap
        @ViewModelKey(MainViewModelImpl::class)
        fun bindMainViewModel(viewModel: MainViewModelImpl): ViewModel

        @Binds
        fun provideActivityModule(activity: MainActivity): AppCompatActivity

        @Binds
        fun bindAppHostProvider(activity: MainActivity): AppNavProvider
    }

    @Module(
        subcomponents = [
            (ListFragmentComponent::class),
            (CreateNoteFragmentComponent::class)
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ClassKey(value = ListFragment::class)
        internal abstract fun bindListFragment(factory: ListFragmentComponent.Factory): AndroidInjector.Factory<*>

        @Binds
        @IntoMap
        @ClassKey(value = CreateNoteFragment::class)
        internal abstract fun bindSignUpFragment(factory: CreateNoteFragmentComponent.Factory): AndroidInjector.Factory<*>

    }


    @Module
    class ActivityModule {
        @Provides
        @Named(MainNavigatorImpl.APP_NAVIGATOR)
        fun provideNavController(holder: ContextHolder): Navigator {
            return AppNavigator(holder)
        }

        @Provides
        @Named(MainNavigatorImpl.APP_NAV_NAVIGATOR)
        fun provideAppNavController(provider: AppNavProvider,
                                 holder: ContextHolder
        ): Navigator {
            return AppNavComponentsNavigator(
                provider.getNavController()
            ) { provider.finish() }
        }

        @Provides
        fun provideNavigator(@Named(MainNavigatorImpl.APP_NAV_NAVIGATOR) navigator: Navigator): MainNavigator =
                MainNavigatorImpl(navigator)
    }

    @Module(
        subcomponents = [

        ]
    )
    interface BindingsModule
}