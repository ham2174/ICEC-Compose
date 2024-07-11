package com.ham.icec.compose.di.repository

import com.ham.icec.compose.data.detect.repository.DetectRepositoryImpl
import com.ham.icec.compose.data.gallery.repository.GalleryRepositoryImpl
import com.ham.icec.compose.data.mosaic.repository.MosaicRepositoryImpl
import com.ham.icec.compose.domain.detect.repository.DetectRepository
import com.ham.icec.compose.domain.gallery.repository.GalleryRepository
import com.ham.icec.compose.domain.mosaic.respotitory.MosaicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGalleryRepository(repository: GalleryRepositoryImpl): GalleryRepository

    @Binds
    @Singleton
    abstract fun bindDetectRepository(repository: DetectRepositoryImpl): DetectRepository

    @Binds
    @Singleton
    abstract fun bindMosaicRepository(repository: MosaicRepositoryImpl): MosaicRepository

}