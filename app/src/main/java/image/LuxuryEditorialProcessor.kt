package com.andy.luxuryportraitstudio.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

object LuxuryEditorialProcessor {

    fun process(
        bitmap: Bitmap
    ): Bitmap {

        val result =
            bitmap.copy(
                Bitmap.Config.ARGB_8888,
                true
            )

        val canvas = Canvas(result)

        val paint = Paint()

        val matrix = ColorMatrix()

        matrix.set(
            floatArrayOf(
                1.05f,0f,0f,0f,0f,
                0f,1.02f,0f,0f,0f,
                0f,0f,0.97f,0f,0f,
                0f,0f,0f,1f,0f
            )
        )

        paint.colorFilter =
            ColorMatrixColorFilter(matrix)

        canvas.drawBitmap(
            result,
            0f,
            0f,
            paint
        )

        return result
    }
}
