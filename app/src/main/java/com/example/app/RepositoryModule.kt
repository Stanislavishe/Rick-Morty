package com.example.app

import com.example.data.RMRepositoryImpl
import com.example.domain.RMRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(impl: RMRepositoryImpl) : RMRepository
}