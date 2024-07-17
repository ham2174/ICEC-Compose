package com.ham.icec.compose.facedetection.service

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.ham.icec.compose.facedetection.model.FaceDetectionResult
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
    @Named("FastMode") private val fastDetectorOptions: FaceDetectorOptions,
    @Named("AccurateMode") private val accurateDetectorOptions: FaceDetectorOptions,
) : DetectionService {

    override fun getFastDetectFaces(
        bitmap: Bitmap,
        orientation: Long
    ): Flow<List<FaceDetectionResult>> = flow {
        val result = FaceDetection.getClient(fastDetectorOptions).process(bitmap, orientation)

        emit(result)
    }.flowOn(Dispatchers.Default)

    override fun getAccurateDetectFaces(
        bitmap: Bitmap,
        orientation: Long
    ): Flow<List<FaceDetectionResult>> = flow {
        val result = FaceDetection.getClient(accurateDetectorOptions).process(bitmap, orientation)

        emit(result)
    }.flowOn(Dispatchers.Default)

}

private suspend fun FaceDetector.process(
    bitmap: Bitmap,
    orientation: Long
): List<FaceDetectionResult> =
    suspendCancellableCoroutine { continuation ->
        val inputImage = InputImage.fromBitmap(bitmap, orientation.toInt())
        val task = this.process(inputImage)

        task.addOnSuccessListener { faces ->
            continuation.resume(faces.mapIndexed { index, face ->
                FaceDetectionResult(
                    id = face.trackingId ?: index,
                    boundingBox = face.boundingBox
                )
            })
        }.addOnFailureListener { e ->
            continuation.resumeWithException(e)
        }.addOnCanceledListener {
            continuation.cancel()
        }.addOnCompleteListener { _ ->
            this.close()
        }

        continuation.invokeOnCancellation {
            this.close()
        }
    }
