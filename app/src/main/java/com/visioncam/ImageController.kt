package com.visioncam

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.min

class ImageController(private val context: Context) {
    
    companion object {
        private const val ZOOM_STEP = 0.1f
        private const val MOVE_STEP = 10
    }
    
    private var currentBitmap: Bitmap? = null
    private var currentScale = 1f
    private var offsetX = 0f
    private var offsetY = 0f
    private var imageView: ImageView? = null
    
    fun attachImageView(imageView: ImageView) {
        this.imageView = imageView
    }
    
    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        if (context is AppCompatActivity) {
            // This should be called from activity with result handler
        }
    }
    
    fun setImage(bitmap: Bitmap) {
        currentBitmap = bitmap
        currentScale = 1f
        offsetX = 0f
        offsetY = 0f
        updateImageView()
    }
    
    fun zoomIn() {
        currentScale = (currentScale + ZOOM_STEP).coerceAtMost(3f)
        updateImageView()
    }
    
    fun zoomOut() {
        currentScale = (currentScale - ZOOM_STEP).coerceAtLeast(0.5f)
        updateImageView()
    }
    
    fun moveLeft() {
        offsetX -= MOVE_STEP
        updateImageView()
    }
    
    fun moveRight() {
        offsetX += MOVE_STEP
        updateImageView()
    }
    
    fun moveUp() {
        offsetY -= MOVE_STEP
        updateImageView()
    }
    
    fun moveDown() {
        offsetY += MOVE_STEP
        updateImageView()
    }
    
    private fun updateImageView() {
        currentBitmap?.let { bitmap ->
            val matrix = Matrix()
            matrix.postScale(currentScale, currentScale)
            matrix.postTranslate(offsetX, offsetY)
            
            val scaledBitmap = Bitmap.createBitmap(
                bitmap,
                0, 0,
                bitmap.width,
                bitmap.height,
                matrix,
                true
            )
            
            imageView?.setImageBitmap(scaledBitmap)
        }
    }
    
    fun getCurrentScale(): Float = currentScale
    fun getOffsetX(): Float = offsetX
    fun getOffsetY(): Float = offsetY
}
