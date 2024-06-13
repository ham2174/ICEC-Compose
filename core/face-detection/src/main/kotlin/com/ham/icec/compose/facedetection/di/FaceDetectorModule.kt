package com.ham.icec.compose.facedetection.di

import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FaceDetectorModule {

    @Provides
    @Singleton
    @Named("FastDetector")
    fun provideFastDetectorClient(): FaceDetector {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .build()

        return FaceDetection.getClient(options)
    }

    @Provides
    @Singleton
    @Named("AccurateDetector")
    fun provideAccurateDetectorClient(): FaceDetector {
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .build()

        return FaceDetection.getClient(options)
    }

}