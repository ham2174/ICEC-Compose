package com.ham.icec.compose.gallery.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.ham.icec.compose.designsystem.R
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.domain.entity.ImageInfo
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun GalleryContents(
    galleryImages: ImmutableList<ImageInfo>,
    fetchNextGalleryList: () -> Unit,
    onNavigateToMosaic: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(
            items = galleryImages,
            key = { _, item -> item.id }
        ) { index, galleryImage ->
            if (index >= galleryImages.size - 1 && galleryImages.size % 20 == 0) {
                fetchNextGalleryList()
            }

            Log.d("리컴포지션 일어나나?", "$index, ${galleryImages.size}")

            GlideImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickableSingle { onNavigateToMosaic(galleryImage.stringUri) },
                imageModel = { galleryImage.stringUri.toUri() },
                previewPlaceholder = painterResource(
                    id = R.drawable.sample_img
                ),
            )
        }
    }
}