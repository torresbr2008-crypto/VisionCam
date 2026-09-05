package com.visioncam

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
    private lateinit var cameraManager: CameraManager
    private lateinit var imageController: ImageController
    private lateinit var permissionManager: PermissionManager
    private lateinit var floatingPanelContainer: FrameLayout
    
    private var floatingPanel: FloatingPanel? = null
    private var controlPanel: ControlPanel? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize managers
        permissionManager = PermissionManager(this)
        cameraManager = CameraManager(this)
        imageController = ImageController(this)
        
        // Get UI elements
        floatingPanelContainer = findViewById(R.id.floatingPanelContainer)
        
        // Check and request permissions
        lifecycleScope.launch {
            if (permissionManager.checkAllPermissions()) {
                initializeUI()
            } else {
                permissionManager.requestAllPermissions()
            }
        }
    }
    
    private fun initializeUI() {
        // Create floating panel
        floatingPanel = FloatingPanel(this) { action ->
            handleFloatingPanelAction(action)
        }
        floatingPanelContainer.addView(floatingPanel)
        
        // Create control panel (initially hidden)
        controlPanel = ControlPanel(this) { action ->
            handleControlPanelAction(action)
        }
    }
    
    private fun handleFloatingPanelAction(action: String) {
        when (action) {
            "OPEN_CAMERA" -> {
                cameraManager.startCamera()
            }
            "CAPTURE" -> {
                cameraManager.captureImage()
            }
            "GALLERY" -> {
                imageController.openGallery()
            }
            "MINIMIZE" -> {
                floatingPanel?.minimize()
            }
            "CLOSE" -> {
                finish()
            }
        }
    }
    
    private fun handleControlPanelAction(action: String) {
        when (action) {
            "ZOOM_IN" -> imageController.zoomIn()
            "ZOOM_OUT" -> imageController.zoomOut()
            "MOVE_LEFT" -> imageController.moveLeft()
            "MOVE_RIGHT" -> imageController.moveRight()
            "MOVE_UP" -> imageController.moveUp()
            "MOVE_DOWN" -> imageController.moveDown()
            "CLOSE" -> controlPanel?.hide()
        }
    }
    
    override fun onResume() {
        super.onResume()
        cameraManager.resumeCamera()
    }
    
    override fun onPause() {
        cameraManager.pauseCamera()
        super.onPause()
    }
    
    override fun onDestroy() {
        cameraManager.releaseCamera()
        super.onDestroy()
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionManager.handlePermissionResult(requestCode, grantResults)) {
            initializeUI()
        }
    }
}
