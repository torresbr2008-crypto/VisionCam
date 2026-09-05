package com.visioncam

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import kotlin.math.abs

class FloatingPanel(context: Context, private val callback: (String) -> Unit) : FrameLayout(context) {
    
    private var lastX = 0f
    private var lastY = 0f
    private var deltaX = 0f
    private var deltaY = 0f
    private var isMinimized = false
    private var isPanelVisible = true
    
    private var panelContent: LinearLayout? = null
    private var minimizedButton: Button? = null
    
    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.floating_panel, this, false)
        addView(view)
        
        panelContent = view as? LinearLayout
        
        // Setup buttons
        view.findViewById<Button>(R.id.btnOpenCamera).setOnClickListener {
            callback("OPEN_CAMERA")
        }
        view.findViewById<Button>(R.id.btnCapture).setOnClickListener {
            callback("CAPTURE")
        }
        view.findViewById<Button>(R.id.btnGallery).setOnClickListener {
            callback("GALLERY")
        }
        view.findViewById<Button>(R.id.btnMinimize).setOnClickListener {
            minimize()
        }
        view.findViewById<Button>(R.id.btnClose).setOnClickListener {
            callback("CLOSE")
        }
        
        isClickable = true
        isFocusable = true
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.rawX
                lastY = event.rawY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                deltaX = event.rawX - lastX
                deltaY = event.rawY - lastY
                
                x += deltaX
                y += deltaY
                
                lastX = event.rawX
                lastY = event.rawY
                return true
            }
        }
        return super.onTouchEvent(event)
    }
    
    fun minimize() {
        isMinimized = true
        panelContent?.visibility = GONE
        layoutParams = LayoutParams(200, 200)
    }
    
    fun expand() {
        isMinimized = false
        panelContent?.visibility = VISIBLE
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }
    
    fun hide() {
        visibility = GONE
        isPanelVisible = false
    }
    
    fun show() {
        visibility = VISIBLE
        isPanelVisible = true
    }
}
