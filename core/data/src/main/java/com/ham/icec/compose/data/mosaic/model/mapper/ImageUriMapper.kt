package com.ham.icec.compose.data.mosaic.model.mapper

import android.net.Uri
import com.ham.icec.compose.domain.mosaic.entity.ImageFile

fun Uri.toDomain(): ImageFile = ImageFile(path = this.toString())