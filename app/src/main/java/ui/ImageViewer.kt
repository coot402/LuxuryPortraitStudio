package com.andy.luxuryportraitstudio.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun ImageViewer(bitmap: Bitmap?) {

    if (bitmap == null) {

        Text("No image selected")

        return
    }

    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "Selected Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        contentScale = ContentScale.Fit
    )
}