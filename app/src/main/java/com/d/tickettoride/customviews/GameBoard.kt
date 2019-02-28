package com.d.tickettoride.customviews

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import com.d.tickettoride.R

class GameBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

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

    private val d = resources.getDrawable(R.drawable.usterrain, null).apply {
        bounds = Rect(0, 0, 2575, 1700)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        d.draw(canvas)
        canvas.apply {
            drawCircle(250F, 350F, 20F, cityPaint)
            drawCircle(125F, 725F, 20F, cityPaint)
            drawLine(250F, 350F, 125F, 725F, routePaint)
        }
    }
}