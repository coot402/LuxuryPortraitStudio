package com.andy.luxuryportraitstudio.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

object CrispDetailProcessor {

    fun process(
        input: Bitmap
    ): Bitmap {

        val output =
            input.copy(
                Bitmap.Config.ARGB_8888,
                true
            )

        val canvas = Canvas(output)

        val paint = Paint()

        paint.isAntiAlias = true

        paint.alpha = 255

        canvas.drawBitmap(
            output,
            0f,
            0f,
            paint
        )

        return output
    }
}
