package com.dboy.statusbar

import android.animation.ValueAnimator
import android.graphics.Color
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.annotation.ColorInt

/**
 * 颜色渐变过度动画
 */
object ColorAnimUtils {



    /**
     * @param startColor 开始的颜色
     * @param fromColor 要变成的颜色
     * @param listener 颜色改变回调
     */
    fun toAnim(
        @ColorInt startColor: Int, @ColorInt fromColor: Int, duration: Long = 400,
        listener: (color: Int) -> Unit
    ): ValueAnimator {
        return ValueAnimator.ofFloat(0f, 1f).apply {
            interpolator = LinearInterpolator()
            this.duration = duration
            addUpdateListener { value ->
                val currentColor = getCurrentColor(
                    value.animatedValue as Float,
                    startColor,
                    fromColor
                )
                listener(currentColor)
            }
        }
    }


    /**
     * 根据fraction值来计算当前的颜色。
     */
    fun getCurrentColor(fraction: Float, startColor: Int, endColor: Int): Int {
        var redCurrent = 0
        var blueCurrent = 0
        var greenCurrent = 0
        var alphaCurrent = 0

        val redStart = Color.red(startColor)
        val blueStart = Color.blue(startColor)
        val greenStart = Color.green(startColor)
        val alphaStart = Color.alpha(startColor)

        val redEnd = Color.red(endColor)
        val blueEnd = Color.blue(endColor)
        val greenEnd = Color.green(endColor)
        val alphaEnd = Color.alpha(endColor)

        val redDifference = redEnd - redStart
        val blueDifference = blueEnd - blueStart
        val greenDifference = greenEnd - greenStart
        val alphaDifference = alphaEnd - alphaStart

        redCurrent = (redStart + fraction * redDifference).toInt()
        blueCurrent = (blueStart + fraction * blueDifference).toInt()
        greenCurrent = (greenStart + fraction * greenDifference).toInt()
        alphaCurrent = (alphaStart + fraction * alphaDifference).toInt()

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent)
    }
}

