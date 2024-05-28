package com.ham.icec.compose.di.datasource

import com.ham.icec.compose.data.datasource.local.LocalImageDataSource
import com.ham.icec.compose.local.datasource.LocalImageDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourcesModule {

    @Binds
    @Singleton
    abstract fun bindsLocalImageDataSource(localImageDataSource: LocalImageDataSourceImpl): LocalImageDataSource

}