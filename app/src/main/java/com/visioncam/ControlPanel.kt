package com.visioncam

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout

class ControlPanel(context: Context, private val callback: (String) -> Unit) : LinearLayout(context) {
    
    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.control_panel, this, false)
        addView(view)
        
        // Setup zoom buttons
        view.findViewById<Button>(R.id.btnZoomIn).setOnClickListener {
            callback("ZOOM_IN")
        }
        view.findViewById<Button>(R.id.btnZoomOut).setOnClickListener {
            callback("ZOOM_OUT")
        }
        
        // Setup movement buttons
        view.findViewById<Button>(R.id.btnMoveLeft).setOnClickListener {
            callback("MOVE_LEFT")
        }
        view.findViewById<Button>(R.id.btnMoveRight).setOnClickListener {
            callback("MOVE_RIGHT")
        }
        view.findViewById<Button>(R.id.btnMoveUp).setOnClickListener {
            callback("MOVE_UP")
        }
        view.findViewById<Button>(R.id.btnMoveDown).setOnClickListener {
            callback("MOVE_DOWN")
        }
        
        // Setup close button
        view.findViewById<Button>(R.id.btnCloseControl).setOnClickListener {
            callback("CLOSE")
        }
        
        visibility = GONE
    }
    
    fun show() {
        visibility = VISIBLE
    }
    
    fun hide() {
        visibility = GONE
    }
}
