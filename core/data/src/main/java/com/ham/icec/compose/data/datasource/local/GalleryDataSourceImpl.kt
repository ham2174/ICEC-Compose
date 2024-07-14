package com.ham.icec.compose.data.datasource.local

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.ham.icec.compose.data.gallery.model.GalleryImageInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class GalleryDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GalleryDataSource {

    override fun fetchMediaStoreImages(page: Int): Flow<List<GalleryImageInfo>> = flow {
        val images = mutableListOf<GalleryImageInfo>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID, // 이미지 ID
            MediaStore.Images.ImageColumns.ORIENTATION, // 이미지 회전각
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC" // 추가된 날짜 내림차순 정렬

        context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
            val orientationColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION)
            val limitOffset = page * LIMIT_COUNT

            if (cursor.moveToPosition(limitOffset)) {
                do {
                    val id = cursor.getLong(idColumn)
                    val orientation = cursor.getLong(orientationColumn)
                    val imageUri = ContentUris.withAppendedId(uri, id)

                    images.add(GalleryImageInfo(id, imageUri, orientation))
                } while (cursor.moveToNext() && images.size < LIMIT_COUNT)
            }
        }

        emit(images.toList())
    }.onEmpty { emit(emptyList()) }

    companion object {
        private const val LIMIT_COUNT = 30
    }

}