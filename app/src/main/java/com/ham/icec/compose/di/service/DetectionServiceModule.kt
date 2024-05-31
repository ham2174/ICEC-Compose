package com.ham.icec.compose.di.service

import com.ham.icec.compose.facedetection.service.DetectionService
import com.ham.icec.compose.facedetection.service.DetectionServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetectionServiceModule {

    @Binds
    abstract fun bindDetectionService(detectionServiceImpl: DetectionServiceImpl): DetectionService

}