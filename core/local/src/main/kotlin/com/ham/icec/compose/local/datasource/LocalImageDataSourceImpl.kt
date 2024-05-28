package com.ham.icec.compose.local.datasource

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.ham.icec.compose.data.datasource.local.LocalImageDataSource
import com.ham.icec.compose.data.model.GalleryImageInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalImageDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalImageDataSource {

    override fun fetchGalleryImages(page: Int): Flow<List<GalleryImageInfo>> = flow {
        val images = mutableListOf<GalleryImageInfo>()

        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME),
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val offset = page * LIMIT_VALUE

            if (cursor.moveToPosition(offset)) {
                for (i in 0 until LIMIT_VALUE) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val imageUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    ).toString()

                    images.add(GalleryImageInfo(page, id, name, imageUri))

                    if(!cursor.moveToNext()) break
                }
            }
        }
        emit(images.toList())
    }.catch { e ->
        e.printStackTrace()
        emit(emptyList())
    }

    companion object {
        private const val LIMIT_VALUE = 20
    }

}