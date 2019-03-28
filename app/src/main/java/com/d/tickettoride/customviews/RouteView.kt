package com.d.tickettoride.customviews

import android.graphics.Paint
import android.graphics.Path
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class RouteView(val id: Int,
                private val city1: Pair<Float, Float>,
                private val city2: Pair<Float, Float>,
                private val path: Path,
                private val dottedPaint: Paint,
                private val selectedPaint: Paint) {

    private var isHorizontal: Boolean = false
    private var isVertical: Boolean = false

    lateinit var claimedPaint: Paint

    enum class RoutePaintType {
        DOTTED, SELECTED, CLAIMED
    }

    var paintType: RoutePaintType = RoutePaintType.DOTTED

    init {
        if (abs(city1.first - city2.first) < 15) isVertical = true
        else if (abs(city1.second - city2.second) < 15) isHorizontal = true
    }

    fun getPaint(): Paint {
        return when(paintType) {
            RoutePaintType.DOTTED -> dottedPaint
            RoutePaintType.SELECTED -> selectedPaint
            RoutePaintType.CLAIMED -> claimedPaint
        }
    }

    fun getPath(): Path {
        return path
    }

    private fun isTouchInHorizontalBox(touchX: Float, touchY: Float): Boolean {
        return isTouchWithinX(touchX) &&
                ((min(city1.second, city2.second) - 15) <= touchY) &&
                (touchY <= (max(city1.second, city2.second) + 15))
    }

    private fun isTouchInVerticalBox(touchX: Float, touchY: Float): Boolean {
        return isTouchWithinY(touchY) &&
                ((min(city1.first, city2.first) - 15) <= touchX) &&
                (touchX <= (max(city1.first, city2.first) + 15))
    }

    private fun isTouchWithinX(touchX: Float): Boolean {
        return (min(city1.first, city2.first) <= touchX) && (touchX <= max(city1.first, city2.first))
    }

    private fun isTouchWithinY(touchY: Float): Boolean {
        return (min(city1.second, city2.second) <= touchY) && (touchY <= max(city1.second, city2.second))
    }

    fun isTouchInRoute(touchX: Float, touchY: Float): Boolean {
        if (isHorizontal) return isTouchInHorizontalBox(touchX, touchY)
        if (isVertical) return isTouchInVerticalBox(touchX, touchY)

        // only calculate slope and distance if necessary
        val distance: Float
        if (isTouchWithinX(touchX) && isTouchWithinY(touchY)) {
            val slope = (city2.second - city1.second) / (city2.first - city1.first)
            distance = abs(touchY - slope*touchX + slope*city1.first - city1.second) / sqrt(1 + slope*slope)
        } else {
            return false
        }

        return distance <= 15
    }
}