@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ham.icec.compose.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ham.icec.compose.domain.entity.ImageInfo
import com.ham.icec.compose.domain.usecase.GetGalleryImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getGalleryImagesUseCase: GetGalleryImagesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<GalleryUiState> = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    private val page: MutableStateFlow<Int> = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            page.flatMapConcat { page ->
                getGalleryImagesUseCase(page = page).map { images ->
                    images.map { info ->
                        ContentImage(
                            id = info.id,
                            stringUri = info.stringUri
                        )
                    }
                }
            }.collect { newImages ->
                val currentImages = _uiState.value.galleryImages

                if (newImages.size < 20) {
                    _uiState.value = _uiState.value.copy(
                        isLastPage = true,
                        galleryImages = currentImages + newImages
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        galleryImages = currentImages + newImages
                    )
                }
            }
        }
    }

    fun fetchNextGalleryList() {
        viewModelScope.launch {
            page.value++
        }
    }

}