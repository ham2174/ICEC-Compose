package com.ham.icec.compose.data.detect.model

import android.graphics.Rect
import com.ham.icec.compose.domain.detect.model.BoundingBox
import com.ham.icec.compose.domain.detect.model.DetectedFace

fun Rect.toBoundingBox() : BoundingBox =
    BoundingBox(
        left = left,
        top = top,
        right = right,
        bottom = bottom
    )

fun List<Rect>.toDomain() : List<DetectedFace> =
    mapIndexed { index, rect ->
        DetectedFace(
            id = index, // TODO : 이미지 캐싱을 위해 검출된 얼굴 이미지에 대한 고유 ID를 어떻게 처리 할지 고민 해보기
            boundingBox = rect.toBoundingBox()
        )
    }