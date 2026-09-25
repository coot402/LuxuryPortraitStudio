package com.andy.luxuryportraitstudio.export

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore

object ImageExporter {

    fun saveBitmap(
        context: Context,
        bitmap: Bitmap
    ) {

        val fileName =
            "LuxuryPortrait_" +
                    System.currentTimeMillis() +
                    ".jpg"

        val values = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                fileName
            )
            put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/jpeg"
            )
        }

        val uri =
            context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )

        uri?.let {

            context.contentResolver
                .openOutputStream(it)
                ?.use { stream ->

                    bitmap.compress(
                        Bitmap.CompressFormat.JPEG,
                        95,
                        stream
                    )
                }
        }
    }
}