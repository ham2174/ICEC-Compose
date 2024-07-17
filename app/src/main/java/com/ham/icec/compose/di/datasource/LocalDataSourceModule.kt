package com.ham.icec.compose.di.datasource

import com.ham.icec.compose.data.datasource.local.DetectDataSource
import com.ham.icec.compose.data.datasource.local.DetectDataSourceImpl
import com.ham.icec.compose.data.datasource.local.GalleryDataSource
import com.ham.icec.compose.data.datasource.local.GalleryDataSourceImpl
import com.ham.icec.compose.data.datasource.local.ImageEditDataSource
import com.ham.icec.compose.data.datasource.local.ImageEditDataSourceImpl
import com.ham.icec.compose.data.datasource.local.MosaicDataSource
import com.ham.icec.compose.data.datasource.local.MosaicDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindsDetectDataSource(dataSourceImpl: DetectDataSourceImpl): DetectDataSource

    @Binds
    @Singleton
    abstract fun bindsMosaicDataSource(dataSourceImpl: MosaicDataSourceImpl): MosaicDataSource

    @Binds
    @Singleton
    abstract fun bindsImageEditDataSource(dataSourceImpl: ImageEditDataSourceImpl): ImageEditDataSource

}