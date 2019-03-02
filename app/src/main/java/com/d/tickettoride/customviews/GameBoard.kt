package com.d.tickettoride.customviews

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import com.d.tickettoride.R
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.model.gameplay.Route

class GameBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var onClaimRouteClicked: ((Int) -> Unit)? = null

    private val cityPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("red")
        style = Paint.Style.FILL
    }

    private var mapDrawable: Drawable? = context.getDrawable(R.drawable.usterrain)

    var cities: Map<Int, City> = HashMap()
    private var routePaths: MutableMap<Int, Pair<Path, Paint>> = HashMap()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("DEBUG", "Hey, we're drawing!")
        mapDrawable!!.setBounds(0, 0, width, height)
        mapDrawable!!.draw(canvas)

        routePaths.let {
            for ((_, pair) in it) {
                canvas.drawPath(pair.first, pair.second)
            }
        }

        cities.let {
            for ((_, city) in it) {
                canvas.drawCircle(city.longitude, city.latitude, 20f, cityPaint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("DEBUG", "onTouchEvent() called")
        when (event.action) {
            ACTION_DOWN -> {
                onClaimRouteClicked?.invoke(0)
            }
        }

        return true
    }

    fun setRouteData(routes: Map<Int, Route>) {
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
                val linePaint = Paint(ANTI_ALIAS_FLAG).apply {
                    if (route.owner == null) {
                        color = Color.parseColor(route.color.toString())
                        strokeWidth = 30f
                        style = Paint.Style.STROKE
                        pathEffect = DashPathEffect(floatArrayOf(dashLength, 10f), 0f)
                    }
                }
                routePaths[route.id] = Pair(linePath, linePaint)
            }
        }
    }

    private fun calculateDashLength(numDashes: Int, x1: Float, y1: Float, x2: Float, y2: Float, off: Float): Float {
        val diffX: Double = (x2 - x1).toDouble()
        val diffY: Double = (y2 - y1).toDouble()
        val distance: Double = Math.sqrt(diffX*diffX + diffY*diffY)
        return ((distance - (off * (numDashes - 1))) / numDashes).toFloat()
    }
}