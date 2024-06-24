package com.ham.icec.compose.facedetection.service

import android.graphics.Rect
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetector
import com.ham.icec.compose.utilandroid.extension.toBitmap
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
    @Named("FastDetector") private val fastDetector: FaceDetector,
    @Named("AccurateDetector") private val accurateDetector: FaceDetector,
) : DetectionService {

    override fun getFastDetectFaces(image: ByteArray): Flow<List<Rect>> = flow {
        emit(fastDetector.process(image))
    }.flowOn(Dispatchers.Default)

    override fun getAccurateDetectFaces(image: ByteArray): Flow<List<Rect>> = flow {
        emit(accurateDetector.process(image))
    }.flowOn(Dispatchers.Default)

    private suspend fun FaceDetector.process(image: ByteArray): List<Rect> =
        suspendCancellableCoroutine { continuation ->
            val inputImage = InputImage.fromBitmap(image.toBitmap(), 0)

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
                .addOnCompleteListener { _ ->
                    this.close()
                }

            continuation.invokeOnCancellation {
                this.close()
            }
        }

}
