package com.darkstoreapp.test.di

import com.darkstoreapp.test.data.repository.*
import com.darkstoreapp.test.domain.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun CurrencyRemoteDataSourceImpl.bindRemote(): CurrencyRemoteDataSource

    @Binds
    @Singleton
    fun CurrencyLocalDataSourceImpl.bindLocal(): CurrencyLocalDataSource

    @Binds
    @Singleton
    fun CurrencyRepositoryImpl.bindRepository(): CurrencyRepository
}