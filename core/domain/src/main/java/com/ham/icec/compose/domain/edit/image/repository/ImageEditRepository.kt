package com.ham.icec.compose.domain.edit.image.repository

import com.ham.icec.compose.domain.detect.entity.BoundingBox
import com.ham.icec.compose.domain.detect.entity.ImageStream
import com.ham.icec.compose.domain.gallery.entity.MediaStoreImage

interface ImageEditRepository {

    suspend fun drawBoundingBoxesOnMediaStoreImage(
        mediaStoreImage: MediaStoreImage,
        boundingBoxes: List<BoundingBox>
    ): Result<ImageStream>

}