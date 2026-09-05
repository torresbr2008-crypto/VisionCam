package com.visioncam

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class FloatingPanelService : Service() {
    
    companion object {
        private const val TAG = "FloatingPanelService"
    }
    
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "FloatingPanelService created")
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "FloatingPanelService started")
        return START_STICKY
    }
    
    override fun onDestroy() {
        Log.d(TAG, "FloatingPanelService destroyed")
        super.onDestroy()
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
}
