package com.andy.luxuryportraitstudio.ui

import android.app.Activity
import androidx.activity.result.PickVisualMediaRequest
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andy.luxuryportraitstudio.export.ImageExporter
import com.andy.luxuryportraitstudio.viewmodel.ImageViewModel
import com.yalantis.ucrop.UCrop
import java.io.File

@Composable
fun HomeScreen(
    vm: ImageViewModel = viewModel()
) {

    val context = LocalContext.current

    // PUT crop variables here
    var cropUri by remember {
        mutableStateOf<Uri?>(null)
    }

    // PUT crop launcher here
    val cropLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                cropUri?.let { uri ->
                    context.contentResolver
                        .openInputStream(uri)
                        ?.use { stream ->
                            val bitmap =
                                BitmapFactory.decodeStream(stream)

                            if (bitmap != null) {
                                vm.setImage(bitmap)
                            }
                        }
                }
            }
        }


    // Existing Photo Picker launcher goes BELOW it
    val photoPicker =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.PickVisualMedia()
        ) { uri ->

            uri?.let {

                cropUri =
                    Uri.fromFile(
                        File(
                            context.cacheDir,
                            "cropped.jpg"
                        )
                    )

                val destinationUri = cropUri!!
                val intent =
                    UCrop.of(
                        uri,
                        destinationUri
                    )
                        .withAspectRatio(4f, 5f)
                        .getIntent(context)

                cropLauncher.launch(intent)
            }
        }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts
                                .PickVisualMedia
                                .ImageOnly
                        )
                    )
                }
            ) {
                Text("Select Photo")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    vm.applyCrispDetail()
                }
            ) {
                Text("Crisp Detail")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    vm.applyLuxuryEditorial()
                }
            ) {
                Text("Luxury Editorial")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    vm.processedBitmap?.let {
                        ImageExporter.saveBitmap(
                            context,
                            it
                        )
                    }
                }
            ) {
                Text("Save Image")
            }
        }
    }
}