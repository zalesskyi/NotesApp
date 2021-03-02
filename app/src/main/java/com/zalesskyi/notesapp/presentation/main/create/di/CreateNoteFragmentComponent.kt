package com.zalesskyi.notesapp.presentation.main.create.di

import androidx.lifecycle.ViewModel
import com.zalesskyi.notesapp.android.di.scopes.PerFragment
import com.zalesskyi.notesapp.android.di.viewmodelfactory.ViewModelKey
import com.zalesskyi.notesapp.presentation.main.create.CreateNoteFragment
import com.zalesskyi.notesapp.presentation.main.create.CreateNoteViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@PerFragment
@Subcomponent(
    modules = [
        (CreateNoteFragmentComponent.FragmentBindingsModule::class),
        (CreateNoteFragmentComponent.FragmentModule::class),
        (CreateNoteFragmentComponent.FragmentBindsModule::class)
    ]
)
interface CreateNoteFragmentComponent : AndroidInjector<CreateNoteFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<CreateNoteFragment>

    @Module(
        subcomponents = [
            // child fragments
        ]
    )
    abstract class FragmentBindingsModule {

        @Binds
        @IntoMap
        @ViewModelKey(CreateNoteViewModelImpl::class)
        abstract fun bindViewModel(viewModel: CreateNoteViewModelImpl): ViewModel
    }

    @Module
    class FragmentModule


    @Module
    interface FragmentBindsModule
}