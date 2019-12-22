package com.dboy.statusbar

import android.animation.ValueAnimator
import android.app.Activity
import android.util.Log
import androidx.annotation.ColorInt
import com.jaeger.library.StatusBarUtil

object ChangeColorAnim {
    var CURRENT_COLOR = 0
    var colorAnim: ValueAnimator? = null


    fun change(activity: Activity, @ColorInt color: Int, alpha: Int, duration: Long) {
        cancelAnim()
        colorAnim = ColorAnimUtils.toAnim(CURRENT_COLOR, color, duration) {
            CURRENT_COLOR = it
            StatusBarUtil.setColor(activity, it, alpha)
        }.apply {
            start()
        }
    }

    fun cancelAnim() {
//        Log.d("DJC","取消动画")
        colorAnim?.removeAllUpdateListeners()
        colorAnim?.removeAllListeners()
        colorAnim?.cancel()
    }
}