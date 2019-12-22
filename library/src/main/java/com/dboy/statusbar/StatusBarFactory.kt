package com.dboy.statusbar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.jaeger.library.StatusBarUtil

class StatusBarFactory(val activity: Activity) : LayoutInflater.Factory2 {

    private var mViewCreateFactory: LayoutInflater.Factory? = null
    private var mViewCreateFactory2: LayoutInflater.Factory2? = null

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? = onCreateView(name, context, attrs)


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        var view: View? = null
        checkAttrs(context, attrs)
        if (mViewCreateFactory2 != null) {
            view = mViewCreateFactory2!!.onCreateView(name, context, attrs)
            if (view == null) {
                view = mViewCreateFactory2!!.onCreateView(null, name, context, attrs)
            }
            return view
        } else if (mViewCreateFactory != null) {
            view = mViewCreateFactory!!.onCreateView(name, context, attrs)
        }
        return view
    }

    private fun checkAttrs(context: Context, attrs: AttributeSet) {
        val statusBarTyped = context.obtainStyledAttributes(attrs, R.styleable.status_color)

        if (statusBarTyped.indexCount != 0) {
            val viewTyped: String? =
                attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "background")
            viewTyped?.apply {
                StatusBarUtil.setColor(activity, Color.parseColor(this))
            }
        }
        statusBarTyped.recycle()
    }

    fun setInterceptFactory(factory: LayoutInflater.Factory) {
        mViewCreateFactory = factory
    }

    fun setInterceptFactory2(factory: LayoutInflater.Factory2) {
        mViewCreateFactory2 = factory
    }
}