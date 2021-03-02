package com.zalesskyi.notesapp.presentation.auth.signUp.di

import androidx.lifecycle.ViewModel
import com.zalesskyi.notesapp.android.di.scopes.PerFragment
import com.zalesskyi.notesapp.android.di.viewmodelfactory.ViewModelKey
import com.zalesskyi.notesapp.presentation.auth.signUp.SignUpFragment
import com.zalesskyi.notesapp.presentation.auth.signUp.SignUpViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (SignUpFragmentComponent.FragmentBindingsModule::class),
        (SignUpFragmentComponent.FragmentModule::class),
        (SignUpFragmentComponent.FragmentBindsModule::class)
    ]
)
interface SignUpFragmentComponent : AndroidInjector<SignUpFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<SignUpFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(SignUpViewModelImpl::class)
        abstract fun bindViewModel(viewModel: SignUpViewModelImpl): ViewModel
    }

    @Module
    class FragmentModule


    @Module
    interface FragmentBindsModule
}