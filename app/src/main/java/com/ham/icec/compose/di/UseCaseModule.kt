package com.ham.icec.compose.di

import com.ham.icec.compose.domain.gallery.usecase.GetPagedGalleryImagesUseCase
import com.ham.icec.compose.domain.gallery.usecase.GetPagedGalleryImagesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindLoadRecentlyImagesUseCase(
        useCaseImpl: GetPagedGalleryImagesUseCaseImpl
    ): GetPagedGalleryImagesUseCase

}