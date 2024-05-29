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

    override fun fetchGalleryImages(page: Int): Flow<List<GalleryImageInfo>> = flow {
        val images = mutableListOf<GalleryImageInfo>()

        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            arrayOf(MediaStore.Images.Media._ID),
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val offset = page * LIMIT_VALUE

            if (cursor.moveToPosition(offset)) {
                for (i in 0 until LIMIT_VALUE) {
                    val id = cursor.getLong(idColumn)
                    val imageUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    images.add(GalleryImageInfo(id, imageUri))

                    if(!cursor.moveToNext()) break
                }
            }
        }
        emit(images.toList())
    }.onEmpty { emit(emptyList()) }

    companion object {
        private const val LIMIT_VALUE = 20
    }

}