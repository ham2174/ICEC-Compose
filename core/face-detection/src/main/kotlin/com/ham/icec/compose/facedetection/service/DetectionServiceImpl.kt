package com.ham.icec.compose.facedetection.service

import android.content.Context
import android.graphics.Rect
import androidx.core.net.toUri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetector
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DetectionServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named("FastDetector") private val fastDetector: FaceDetector,
    @Named("AccurateDetector") private val accurateDetector: FaceDetector,
) : DetectionService {

    override fun getFastDetectFaces(image: String): Flow<List<Rect>> = flow {
        emit(fastDetector.process(image))
    }.flowOn(Dispatchers.Default)

    override fun getAccurateDetectFaces(image: String): Flow<List<Rect>> = flow {
        emit(accurateDetector.process(image))
    }.flowOn(Dispatchers.Default)

    private suspend fun FaceDetector.process(image: String): List<Rect> =
        suspendCancellableCoroutine { continuation ->
            val inputImage = InputImage.fromFilePath(context, image.toUri())

            this.process(inputImage)
                .addOnSuccessListener { faces ->
                    continuation.resume(faces.map { it.boundingBox })
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
                .addOnCanceledListener {
                    continuation.cancel()
                }

            continuation.invokeOnCancellation {
                this.close()
            }
        }

}
