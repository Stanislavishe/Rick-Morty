package com.example.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.load.model.GlideUrl
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GlideImagePreview(
    data: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit
) {
    if(data == null)
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    else
        GlideImage(
            imageModel = { data },
            modifier = modifier,
            imageOptions = ImageOptions(
                contentScale = contentScale
            )
        )
}