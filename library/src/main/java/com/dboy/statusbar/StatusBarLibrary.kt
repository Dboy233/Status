package com.dboy.statusbar

import android.app.Activity
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat

object StatusBarLibrary {

    fun inject(activity: Activity) {
        val inflater: LayoutInflater?
        inflater = activity.layoutInflater
        if (inflater.factory2 == null) {
            inflater.factory2 = StatusBarFactory(activity)
        } else if (inflater.factory2 !is StatusBarFactory) {
            forceSetFactory2(inflater, StatusBarFactory(activity))
        }
    }

    private fun forceSetFactory2(inflater: LayoutInflater, factory: StatusBarFactory) {
        val compatClass = LayoutInflaterCompat::class.java
        val inflaterClass = LayoutInflater::class.java
        try {
            val sCheckedField = compatClass.getDeclaredField("sCheckedField")
            sCheckedField.isAccessible = true
            sCheckedField.setBoolean(inflater, false)
            val mFactory = inflaterClass.getDeclaredField("mFactory")
            mFactory.isAccessible = true
            val mFactory2 = inflaterClass.getDeclaredField("mFactory2")
            mFactory2.isAccessible = true
            if (inflater.factory2 != null) {
                factory.setInterceptFactory2(inflater.factory2)
            } else if (inflater.factory != null) {
                factory.setInterceptFactory(inflater.factory)
            }
            mFactory2.set(inflater, factory)
            mFactory.set(inflater, factory)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

    }
}