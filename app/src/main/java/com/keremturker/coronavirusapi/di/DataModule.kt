package com.keremturker.coronavirusapi.di

import com.keremturker.coronavirusapi.repository.network.APIClient
import com.keremturker.coronavirusapi.repository.network.APIClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindAPIClientImpl(impl: APIClientImpl): APIClient
}