package com.andy.luxuryportraitstudio.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.andy.luxuryportraitstudio.image.CrispDetailProcessor
import com.andy.luxuryportraitstudio.image.LuxuryEditorialProcessor
import android.util.Log

class ImageViewModel : ViewModel() {

    var originalBitmap by mutableStateOf<Bitmap?>(null)

    var processedBitmap by mutableStateOf<Bitmap?>(null)

    fun setImage(bitmap: Bitmap) {

//        android.util.Log.d(
//            "IMAGE",
//            "setImage called"
//        )

        originalBitmap = bitmap
        processedBitmap = bitmap
    }

    fun applyCrispDetail() {
        originalBitmap?.let {
            processedBitmap = CrispDetailProcessor.process(it)
        }
    }

    fun applyLuxuryEditorial() {
        originalBitmap?.let {
            processedBitmap = LuxuryEditorialProcessor.process(it)
        }
    }
}
