package com.denis.mvi_recyclerview_coil_hilt_espresso_junit.di

import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.DefaultDispatcherProvider
import com.denis.mvi_recyclerview_coil_hilt_espresso_junit.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoroutinesModule {
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider
}