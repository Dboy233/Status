package com.dboy.statusbar

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class StatusBarLifecycleRegister : Application.ActivityLifecycleCallbacks {


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d("DJC","onActivityCreated")
        StatusBarLibrary.inject(activity)
//        val obtainStyledAttributes = activity.obtainStyledAttributes(R.styleable.status_color)
//        val color = obtainStyledAttributes.getColor(R.styleable.status_color_statusBar_color, 0)
//        if (color == 0) {
//            Log.d("DJC", "00000")
//        } else {
//            Log.d("DJC","颜色 $color")
//        }
//        obtainStyledAttributes?.let {
//         Log.d("DJC","不为空")
//
//        }
//
//
//        obtainStyledAttributes.recycle()
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }


}