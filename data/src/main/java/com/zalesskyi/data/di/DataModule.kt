package com.zalesskyi.data.di

import com.zalesskyi.data.repository.AuthDataRepository
import com.zalesskyi.data.repository.MediaDataRepository
import com.zalesskyi.data.repository.NotesDataRepository
import com.zalesskyi.domain.repository.AuthRepository
import com.zalesskyi.domain.repository.MediaRepository
import com.zalesskyi.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun provideAuthRepository(repository: AuthDataRepository): AuthRepository

    @Binds
    abstract fun provideNotesRepository(repository: NotesDataRepository): NotesRepository

    @Binds
    abstract fun provideMediaRepository(repository: MediaDataRepository): MediaRepository
}