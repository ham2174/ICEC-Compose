package com.ham.icec.compose.data.datasource.local

import android.net.Uri

interface MosaicDataSource {

    suspend fun saveImage(image: ByteArray): Result<Uri>

}