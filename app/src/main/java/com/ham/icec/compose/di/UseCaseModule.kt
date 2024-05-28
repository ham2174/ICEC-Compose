package com.ham.icec.compose.di

import com.ham.icec.compose.domain.usecase.GetGalleryImagesUseCase
import com.ham.icec.compose.domain.usecase.GetGalleryImagesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindLoadRecentlyImagesUseCase(
        loadRecentlyImagesUseCaseImpl: GetGalleryImagesUseCaseImpl
    ): GetGalleryImagesUseCase

}