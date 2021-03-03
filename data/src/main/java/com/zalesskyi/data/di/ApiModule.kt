package com.zalesskyi.data.di

import com.zalesskyi.data.network.api.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideAuthApi(): AuthApi = AuthApiImpl()

    @Singleton
    @Provides
    fun provideNotesApi(): NotesApi = NotesApiImpl()

    @Singleton
    @Provides
    fun provideMediaApi(): MediaApi = MediaApiImpl()
}