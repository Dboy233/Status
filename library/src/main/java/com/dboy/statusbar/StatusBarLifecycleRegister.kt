package com.dboy.statusbar

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class StatusBarLifecycleRegister : Application.ActivityLifecycleCallbacks {


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Log.d("DJC","onActivityCreated")
        StatusBarLibrary.inject(activity)
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        ChangeColorAnim.cancelAnim()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }


}