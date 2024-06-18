package com.ham.icec.compose.detect

import com.ham.icec.compose.domain.detect.model.DetectedFace

data class DetectUiState(
    val centerImage: String = "",
    val resizedImage: ByteArray = ByteArray(0),
    val detectedImages: List<DetectedImage> = emptyList(),
    val selectedImages: List<DetectedFace> = emptyList(),
    val isLoading: Boolean = true,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DetectUiState

        if (centerImage != other.centerImage) return false
        if (!resizedImage.contentEquals(other.resizedImage)) return false
        if (detectedImages != other.detectedImages) return false
        if (selectedImages != other.selectedImages) return false
        if (isLoading != other.isLoading) return false

        return true
    }

    override fun hashCode(): Int {
        var result = centerImage.hashCode()
        result = 31 * result + resizedImage.contentHashCode()
        result = 31 * result + detectedImages.hashCode()
        result = 31 * result + selectedImages.hashCode()
        result = 31 * result + isLoading.hashCode()
        return result
    }
}

data class DetectedImage(
    val face: DetectedFace,
    val isSelected: Boolean
)
