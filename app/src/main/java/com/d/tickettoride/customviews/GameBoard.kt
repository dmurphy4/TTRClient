package com.d.tickettoride.customviews

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.model.gameplay.Route
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class GameBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val cityPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("red")
        style = Paint.Style.FILL
    }

    var cities: Map<Int, City> = HashMap()

    private val routeWidth = 30f

    private var routePaths: MutableMap<Int, RouteView> = HashMap()
    private var previousClickId: Int = 0
    private var mapDrawable: Drawable? = context.getDrawable(R.drawable.usterrain)


    var onRouteClicked: ((id: Int) -> Unit)? = null

    fun changeRoutePaintToClaimed(id: Int, playerColor: String) {
        routePaths[id]?.claimedPaint = Paint(ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor(playerColor)
            style = Paint.Style.STROKE
            strokeWidth = 30f
        }
        routePaths[id]?.paintType = RouteView.RoutePaintType.CLAIMED
        invalidate()
    }

    fun highlightRoute(id: Int) {
        routePaths[previousClickId]?.paintType = RouteView.RoutePaintType.DOTTED
        routePaths[id]?.paintType = RouteView.RoutePaintType.SOLID
        previousClickId = id
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("DEBUG", "Hey, we're drawing!")
        mapDrawable!!.setBounds(0, 0, width, height)
        mapDrawable!!.draw(canvas)

        routePaths.let {
            for ((_, route) in it) {
                canvas.drawPath(route.getPath(), route.getPaint())
            }
        }

        cities.let {
            for ((_, city) in it) {
                canvas.drawCircle(city.longitude, city.latitude, 20f, cityPaint)
            }
        }
    }

    fun setRouteData(routes: Map<Int, Route>, userColor: String) {
        routePaths.clear()
        routes.let {
            for ((_, route) in it) {
                val linePath = Path()
                val city1 = cities[route.city1]
                val city2 = cities[route.city2]
                linePath.moveTo(city1!!.longitude, city1.latitude)
                linePath.lineTo(city2!!.longitude, city2.latitude)
                val dashLength = calculateDashLength(route.numTracks, city1.longitude, city1.latitude,
                                                     city2.longitude, city2.latitude, 10f)
                val dottedPaint = Paint(ANTI_ALIAS_FLAG).apply {
                    if (route.owner == null) {
                        color = Color.parseColor(route.color.toString())
                        strokeWidth = routeWidth
                        style = Paint.Style.STROKE
                        pathEffect = DashPathEffect(floatArrayOf(dashLength, 10f), 0f)
                    }
                }
                val solidPaint = Paint(ANTI_ALIAS_FLAG).apply {
                    color = Color.parseColor(userColor)
                    style = Paint.Style.STROKE
                    strokeWidth = 30f
                    pathEffect = DashPathEffect(floatArrayOf(dashLength, 10f), 0f)
                }
                routePaths[route.id] = RouteView(route.id, Pair(city1.longitude, city1.latitude),
                    Pair(city2.longitude, city2.latitude), linePath, dottedPaint, solidPaint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                for ((id, route) in routePaths) {
                    if (route.isTouchInRoute(x, y)) {
                        onRouteClicked?.invoke(id)
                        return true
                    }
                }
            }
        }
        return true
    }

    private fun calculateDashLength(numDashes: Int, x1: Float, y1: Float, x2: Float, y2: Float, off: Float): Float {
        val diffX: Double = (x2 - x1).toDouble()
        val diffY: Double = (y2 - y1).toDouble()
        val distance: Double = Math.sqrt(diffX*diffX + diffY*diffY)
        return ((distance - (off * (numDashes - 1))) / numDashes).toFloat()
    }
}