package com.ham.icec.compose.gallery.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.ham.icec.compose.designsystem.modifier.clickableSingle
import com.ham.icec.compose.gallery.ContentImage
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun GalleryContents(
    lazyGridState: LazyGridState,
    photos: ImmutableList<ContentImage>,
    onClickPhoto: (uri: String, orientation: Long) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyGridState,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            items = photos,
            key = { item -> item.id }
        ) { photos ->
            val model = remember(photos.stringUri) { photos.stringUri.toUri() }

            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickableSingle{ onClickPhoto(photos.stringUri, photos.orientation) },
                contentDescription = null,
                model = model,
            )
        }
    }
}