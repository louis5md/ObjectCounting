package com.example.mycountingobject.ui.component

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions
import java.util.concurrent.Executors

@SuppressLint("PermissionLaunchedDuringComposition", "UnsafeOptInUsageError")
@Composable
fun MyCamera(
    modifier: Modifier = Modifier,
    isCounting: Boolean,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val tracker = remember { mutableSetOf<Int>() }
    var count = remember { 0 }
    if (isCounting){
        AndroidView(
            factory = { ctx ->
                // Preview View
                val previewView = PreviewView(ctx)
                val relativeLayout = RelativeLayout(ctx)
                val previewParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
                relativeLayout.addView(previewView, previewParams)

                // view for vertical line
                val verticalLine = View(ctx)
                verticalLine.setBackgroundColor(android.graphics.Color.GREEN)
                val lineParams = LayoutParams(
                    2,
                    LayoutParams.MATCH_PARENT
                )
                lineParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
                relativeLayout.addView(verticalLine, lineParams)

                val executor = ContextCompat.getMainExecutor(ctx)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    val cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()

                    val localModel = LocalModel.Builder()
                        .setAssetFilePath("object_labeler.tflite")
                        .build()

                    val customObjectDetectionOptions = CustomObjectDetectorOptions.Builder(localModel)
                        .enableClassification()
                        .setClassificationConfidenceThreshold(0.5f)
                        .setMaxPerObjectLabelCount(3)
                        .build()

                    val objectDetector : ObjectDetector = ObjectDetection.getClient(customObjectDetectionOptions)

                    val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()


                    imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor()){imageProxy->
                        val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                        val image = imageProxy.image

                        image?.let {img->
                            val w = img.width / 2
                            val processImage = InputImage.fromMediaImage(img, rotationDegrees)
                            objectDetector
                                .process(processImage)
                                .addOnSuccessListener{objects ->
                                    for(obj in objects){
                                        if(obj.boundingBox.centerX() < w){
                                            Log.e("TEST_COUNTER", "width: $w - cord: ${obj.boundingBox.centerX()}")
                                            obj.trackingId?.let { id -> tracker.add(id) }
                                        }
                                    }
                                    for(idx in tracker){
                                        val temp = objects.find { it.trackingId == idx }
                                        if(temp != null){
                                            if (temp.boundingBox.centerX() > w){
                                                tracker.remove(idx)
                                                count += 1
                                                Log.e("TEST_COUNTER", "count: $count")
                                            }
                                        }
                                    }
                                    Log.e("TEST_COUNTER", "count: $count")
                                    Log.e("TEST_COUNTER", "size: ${tracker.size}")
                                    imageProxy.close()
                                }
                                .addOnFailureListener{e->
                                    Log.e("MyCamera", "Error - ${e.message}")
                                    imageProxy.close()
                                }
                        }


                    }

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        imageAnalysis,
                        preview
                    )
                }, executor)
                relativeLayout
            },
            modifier = modifier.fillMaxSize(),

        )
    } else {
        cameraProviderFuture.get().unbindAll()
        Box(modifier = modifier
            .fillMaxSize()
            .background(Color.Black))
    }
}