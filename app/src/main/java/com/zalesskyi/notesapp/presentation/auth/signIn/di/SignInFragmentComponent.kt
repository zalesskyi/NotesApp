package com.zalesskyi.notesapp.presentation.auth.signIn.di

import androidx.lifecycle.ViewModel
import com.zalesskyi.notesapp.android.di.scopes.PerFragment
import com.zalesskyi.notesapp.android.di.viewmodelfactory.ViewModelKey
import com.zalesskyi.notesapp.presentation.auth.signIn.SignInFragment
import com.zalesskyi.notesapp.presentation.auth.signIn.SignInViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (SignInFragmentComponent.FragmentBindingsModule::class),
        (SignInFragmentComponent.FragmentModule::class),
        (SignInFragmentComponent.FragmentBindsModule::class)
    ]
)
interface SignInFragmentComponent : AndroidInjector<SignInFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<SignInFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(SignInViewModelImpl::class)
        abstract fun bindViewModel(viewModel: SignInViewModelImpl): ViewModel
    }

    @Module
    class FragmentModule


    @Module
    interface FragmentBindsModule
}