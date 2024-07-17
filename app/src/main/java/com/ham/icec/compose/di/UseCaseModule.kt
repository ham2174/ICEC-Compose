package com.ham.icec.compose.di

import com.ham.icec.compose.domain.detect.usecase.GetDetectedFacesUseCase
import com.ham.icec.compose.domain.detect.usecase.GetDetectedFacesUseCaseImpl
import com.ham.icec.compose.domain.edit.image.usecase.DrawBoundingBoxesOnMediaStoreImageUseCase
import com.ham.icec.compose.domain.edit.image.usecase.DrawBoundingBoxesOnMediaStoreImageUseCaseImpl
import com.ham.icec.compose.domain.gallery.usecase.GetPagedGalleryImagesUseCase
import com.ham.icec.compose.domain.gallery.usecase.GetPagedGalleryImagesUseCaseImpl
import com.ham.icec.compose.domain.mosaic.usecase.SaveEffectedImageUseCase
import com.ham.icec.compose.domain.mosaic.usecase.SaveEffectedImageUseCaseImpl
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

    @Binds
    abstract fun bindGetDetectedFacesUseCase(
        useCaseImpl: GetDetectedFacesUseCaseImpl
    ): GetDetectedFacesUseCase

    @Binds
    abstract fun bindSaveEffectedImageUseCase(
        useCaseImpl: SaveEffectedImageUseCaseImpl
    ): SaveEffectedImageUseCase

    @Binds
    abstract fun bindDrawBoundingBoxesOnMediaStoreImageUseCase(
        useCaseImpl: DrawBoundingBoxesOnMediaStoreImageUseCaseImpl
    ): DrawBoundingBoxesOnMediaStoreImageUseCase

}