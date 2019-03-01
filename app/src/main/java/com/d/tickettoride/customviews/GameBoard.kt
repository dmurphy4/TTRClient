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
import com.d.tickettoride.model.gameplay.Board

class GameBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var onRouteClicked: ((Int) -> Unit)? = null

    private val cityPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("red")
        style = Paint.Style.FILL
    }

    private val routePaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("blue")
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(50F, 10F, 50F, 10F), 0F)
        strokeWidth = 20F
    }

    private var mapDrawable: Drawable? = context.getDrawable(R.drawable.usterrain)

    private var eventTouchX = 0f
    private var eventTouchY = 0f
    var board: Board? = null

    init {
        mapDrawable?.setBounds(0, 0, width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("DEBUG", "Hey, we're drawing!")
        mapDrawable!!.draw(canvas)

        board?.routes?.let {
            for ((_, route) in it) {
                val city1 = board?.cities?.get(route.city1)
                val city2 = board?.cities?.get(route.city2)
                canvas.drawLine(city1!!.longitude, city1!!.latitude, city2!!.longitude, city2!!.latitude, routePaint)
            }
        }

        board?.cities?.let {
            for ((_, city) in it) {
                canvas.drawCircle(city.longitude, city.latitude, 20f, cityPaint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("DEBUG", "onTouchEvent() called")
        eventTouchX = event.x
        eventTouchY = event.y
        when (event.action) {
            ACTION_DOWN -> {
                onRouteClicked?.invoke(1)
            }
        }
        return true
    }
}


//drawCircle(250F, 350F, 20F, cityPaint)
//drawCircle(125F, 725F, 20F, cityPaint)
//drawCircle(630F, 640F, 20F, cityPaint)
//drawCircle(300F, 215F, 20F, cityPaint)
//drawCircle(325F, 85F, 20F, cityPaint)
//drawCircle(620F, 80F, 20F, cityPaint)
//drawCircle(825F, 425F, 20F, cityPaint)
//drawCircle(435F, 860F, 20F, cityPaint)
//drawCircle(300F, 1000F, 20F, cityPaint)
//drawCircle(575F, 1100F, 20F, cityPaint)
//drawCircle(850F, 1200F, 20F, cityPaint)
//drawCircle(870F, 990F, 20F, cityPaint)
//drawCircle(890F, 775F, 20F, cityPaint)
//canvas.apply {
//    drawLine(250F, 350F, 125F, 725F, routePaint)
//    drawLine(250F, 350F, 630F, 640F, routePaint)
//    drawLine(630F, 640F, 125F, 725F, routePaint)
//}