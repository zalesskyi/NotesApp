package com.zalesskyi.notesapp.presentation.main.list.di

import androidx.lifecycle.ViewModel
import com.zalesskyi.notesapp.android.di.scopes.PerFragment
import com.zalesskyi.notesapp.android.di.viewmodelfactory.ViewModelKey
import com.zalesskyi.notesapp.presentation.main.list.ListFragment
import com.zalesskyi.notesapp.presentation.main.list.ListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (ListFragmentComponent.FragmentBindingsModule::class),
        (ListFragmentComponent.FragmentModule::class),
        (ListFragmentComponent.FragmentBindsModule::class)
    ]
)
interface ListFragmentComponent : AndroidInjector<ListFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<ListFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(ListViewModelImpl::class)
        abstract fun bindViewModel(viewModel: ListViewModelImpl): ViewModel
    }

    @Module
    class FragmentModule


    @Module
    interface FragmentBindsModule
}