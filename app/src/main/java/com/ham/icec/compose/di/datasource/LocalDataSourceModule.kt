package com.ham.icec.compose.di.datasource

import com.ham.icec.compose.data.datasource.local.GalleryDataSource
import com.ham.icec.compose.data.datasource.local.GalleryDataSourceImpl
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
    abstract fun bindsGalleryDataSource(dataSourceImpl: GalleryDataSourceImpl): GalleryDataSource

}