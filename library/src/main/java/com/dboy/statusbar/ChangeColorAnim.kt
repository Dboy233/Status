package com.dboy.statusbar

import android.animation.ValueAnimator
import android.app.Activity
import android.util.Log
import androidx.annotation.ColorInt
import com.jaeger.library.StatusBarUtil

object ChangeColorAnim {
    var CURRENT_COLOR = 0

    val animList: HashMap<String, ValueAnimator> = HashMap()

    fun change(activity: Activity, @ColorInt color: Int, alpha: Int, duration: Long) {
        val key = activity.localClassName
        if (animList.containsKey(key)) {
            animList[key]?.apply {
                removeAllUpdateListeners()
                removeAllListeners()
                cancel()
            }
        }
        Log.d("DJC", "加载动画 $key")
        animList[key] =
            ColorAnimUtils.toAnim(CURRENT_COLOR, color, duration) {
                CURRENT_COLOR = it
                StatusBarUtil.setColor(activity, it, alpha)
            }.apply {
                start()
            }
    }

    fun cancelAnim(activity: Activity) {
        val key = activity.localClassName
        if (animList.containsKey(key)) {
            Log.d("DJC", "取消动画 $key")
            animList[key]?.apply {
                removeAllUpdateListeners()
                removeAllListeners()
                cancel()
            }
            animList.remove(key)
        }


    }
}