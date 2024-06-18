package com.example.calculator

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class HapticFeedBack {
    @RequiresApi(Build.VERSION_CODES.O)
    fun triggerHapticFeedback(context: Context, duration: Long) {
        val vibrator = ContextCompat.getSystemService(context, Vibrator::class.java)
        vibrator?.let {
            if (it.hasVibrator()){
                val vibrationEffect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
                it.vibrate(vibrationEffect)
            }
        }
    }
}