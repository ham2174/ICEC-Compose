package com.ham.icec.compose.data.datasource.local

import android.graphics.Rect
import com.ham.icec.compose.facedetection.service.DetectionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class DetectDataSourceImpl @Inject constructor(
    private val detectionService: DetectionService
) : DetectDataSource {

    override fun getDetectedFaceBoundingBoxes(imagePath: String): Flow<List<Rect>> =
        combine(
            detectionService.getFastDetectFaces(imagePath),
            detectionService.getAccurateDetectFaces(imagePath)
        ) { fastModeResult, accurateModeResult ->
            val result = MutableList(accurateModeResult.size) { accurateModeResult[it] }

            for (fastResult in fastModeResult) {
                if (!result.any { rect -> isSimilarFace(rect1 = rect, rect2 = fastResult) }) {
                    result.add(fastResult)
                }
            }

            result.toList()
        }.flowOn(Dispatchers.IO)

    private fun isSimilarFace(rect1: Rect, rect2: Rect): Boolean {
        val threshold = 0.5 // 50% 이내 차이가 나는 경우 같은 얼굴로 판단

        val centerX1 = rect1.centerX().toDouble()
        val centerY1 = rect1.centerY().toDouble()
        val centerX2 = rect2.centerX().toDouble()
        val centerY2 = rect2.centerY().toDouble()

        val width1 = rect1.width().toDouble()
        val height1 = rect1.height().toDouble()
        val width2 = rect2.width().toDouble()
        val height2 = rect2.height().toDouble()

        val positionDifferenceX = abs(centerX1 - centerX2)
        val positionDifferenceY = abs(centerY1 - centerY2)

        val sizeDifferenceWidth = abs(width1 - width2)
        val sizeDifferenceHeight = abs(height1 - height2)

        val distanceBetweenCenters = sqrt(positionDifferenceX.pow(2) + positionDifferenceY.pow(2))
        val maxDimension = maxOf(width1, height1, width2, height2)

        val sizeDifferenceRatio =
            (sizeDifferenceWidth / maxDimension + sizeDifferenceHeight / maxDimension) / 2

        return distanceBetweenCenters < threshold * maxDimension && sizeDifferenceRatio < threshold
    }

}
