package com.ham.icec.compose.facedetection.service

import android.graphics.Rect
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
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
    @Named("FastMode") private val fastDetectorOptions: FaceDetectorOptions,
    @Named("AccurateMode") private val accurateDetectorOptions: FaceDetectorOptions,
) : DetectionService {

    override fun getFastDetectFaces(image: ByteArray, orientation: Long): Flow<List<Rect>> = flow {
        val detector = FaceDetection.getClient(fastDetectorOptions)
        val result = detector.process(image, orientation)

        emit(result)
    }.flowOn(Dispatchers.Default)

    override fun getAccurateDetectFaces(image: ByteArray, orientation: Long): Flow<List<Rect>> = flow {
        val detector = FaceDetection.getClient(accurateDetectorOptions)
        val result = detector.process(image, orientation)

        emit(result)
    }.flowOn(Dispatchers.Default)

}

private suspend fun FaceDetector.process(image: ByteArray, orientation: Long): List<Rect> =
    suspendCancellableCoroutine { continuation ->
        val inputImage = InputImage.fromBitmap(image.toBitmap(), orientation.toInt())

        Log.d("이미지 회전각", inputImage.rotationDegrees.toString())
        Log.d("이미지 높이", inputImage.height.toString())
        Log.d("이미지 너비", inputImage.width.toString())

        val task = this.process(inputImage)

        task.addOnSuccessListener { faces ->
            continuation.resume(faces.map {
                Log.d("바운딩 박스", it.boundingBox.toString())
                it.boundingBox
            })
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
