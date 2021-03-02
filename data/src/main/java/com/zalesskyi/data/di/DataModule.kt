package com.zalesskyi.data.di

import com.zalesskyi.data.repository.AuthDataRepository
import com.zalesskyi.data.repository.MediaDataRepository
import com.zalesskyi.domain.repository.AuthRepository
import com.zalesskyi.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun provideMediaRepository(repository: MediaDataRepository): MediaRepository

    @Binds
    abstract fun provideAuthRepository(repository: AuthDataRepository): AuthRepository
}