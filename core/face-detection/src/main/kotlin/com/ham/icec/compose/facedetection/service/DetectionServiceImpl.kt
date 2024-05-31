package com.ham.icec.compose.facedetection.service

import android.content.Context
import android.graphics.Rect
import android.util.Log
import androidx.core.net.toUri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.ham.icec.compose.facedetection.model.PerformanceMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DetectionServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DetectionService {

    override suspend fun detectFaces(
        image: String,
        performanceMode: PerformanceMode
    ): List<Rect> {
        Log.d("이미지 패스", image)
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(performanceMode.type)
            .build()
        val detector = FaceDetection.getClient(options)
        val inputImage = InputImage.fromFilePath(context, image.toUri())

        return suspendCancellableCoroutine { continuation ->
            with(continuation) {
                detector.process(inputImage)
                    .addOnSuccessListener { faces ->
                        resume(faces.map { it.boundingBox })
                    }
                    .addOnFailureListener { e ->
                        resumeWithException(e)
                    }
                    .addOnCanceledListener {
                        cancel()
                    }
            }
        }
    }

}
