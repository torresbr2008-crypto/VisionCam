package com.visioncam

import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.util.Log
import android.view.TextureView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraManager(private val context: Context) : TextureView.SurfaceTextureListener {
    
    companion object {
        private const val TAG = "CameraManager"
        private var frontCamera: Camera? = null
        private var rearCamera: Camera? = null
        private var currentCamera: Camera? = null
        private var cameraId = 0 // 0 = rear, 1 = front
    }
    
    private var textureView: TextureView? = null
    private var isPreviewRunning = false
    
    fun attachTextureView(textureView: TextureView) {
        this.textureView = textureView
        textureView.surfaceTextureListener = this
    }
    
    fun startCamera() {
        try {
            if (cameraId == 0) {
                rearCamera = Camera.open(0)
                currentCamera = rearCamera
            } else {
                frontCamera = Camera.open(1)
                currentCamera = frontCamera
            }
            
            val params = currentCamera?.parameters
            params?.previewSize?.let {
                Log.d(TAG, "Camera started: ${it.width}x${it.height}")
            }
            
            isPreviewRunning = true
        } catch (e: Exception) {
            Log.e(TAG, "Error starting camera", e)
        }
    }
    
    fun switchCamera() {
        releaseCamera()
        cameraId = if (cameraId == 0) 1 else 0
        startCamera()
    }
    
    fun captureImage() {
        try {
            currentCamera?.takePicture(
                null,
                null,
                Camera.PictureCallback { data, _ ->
                    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
                    val fileName = "IMG_$timestamp.jpg"
                    val file = File(context.cacheDir, fileName)
                    file.writeBytes(data)
                    Log.d(TAG, "Image captured: ${file.absolutePath}")
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error capturing image", e)
        }
    }
    
    fun resumeCamera() {
        if (!isPreviewRunning) {
            startCamera()
        }
    }
    
    fun pauseCamera() {
        try {
            currentCamera?.stopPreview()
            isPreviewRunning = false
        } catch (e: Exception) {
            Log.e(TAG, "Error pausing camera", e)
        }
    }
    
    fun releaseCamera() {
        try {
            currentCamera?.stopPreview()
            currentCamera?.release()
            rearCamera?.release()
            frontCamera?.release()
            currentCamera = null
            isPreviewRunning = false
        } catch (e: Exception) {
            Log.e(TAG, "Error releasing camera", e)
        }
    }
    
    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        try {
            currentCamera?.setPreviewTexture(surface)
            currentCamera?.startPreview()
            isPreviewRunning = true
        } catch (e: Exception) {
            Log.e(TAG, "Error setting preview", e)
        }
    }
    
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {}
    
    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean = false
    
    override fun onSurfaceTextureFrameAvailable(surface: SurfaceTexture) {}
}
