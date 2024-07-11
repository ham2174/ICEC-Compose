package com.ham.icec.compose.data.datasource.local

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.ham.icec.compose.utilandroid.extension.toBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class MosaicDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MosaicDataSource {

    override suspend fun saveImage(image: ByteArray): Result<Uri> = runCatching {
        withContext(Dispatchers.IO) {
            val bitmapImage = image.toBitmap()
            val fileName = "icec_${System.currentTimeMillis()}.png"
            val directory = File(context.getExternalFilesDir(null), DIRECTORY_NAME).also { file ->
                if (!file.exists()) {
                    file.mkdirs()
                }
            }
            val file = File(directory, fileName)

            FileOutputStream(file).use { outputStream ->
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }

            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        }
    }

    companion object {
        private const val DIRECTORY_NAME = "ICEC_MOSAIC"
    }

}