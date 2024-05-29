package com.ham.icec.compose.di.repository

import com.ham.icec.compose.data.gallery.repository.GalleryRepositoryImpl
import com.ham.icec.compose.domain.gallery.repository.GalleryRepository
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

}