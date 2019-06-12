package hu.ait.tictactoe.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackGround : Paint? = null
    var paintLine : Paint? = null

    //var cX: Float = -1f
    //var cY: Float = -1f


    var circles = mutableListOf<PointF>()


    init {
        paintBackGround = Paint()
        paintBackGround?.color = Color.BLACK
        paintBackGround?.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine?.color = Color.WHITE
        paintLine?.style = Paint.Style.STROKE
        paintLine?.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f,
            width.toFloat(), height.toFloat(), paintBackGround!!)

        canvas?.drawLine(0f, 0f,
            width.toFloat(), height.toFloat(), paintLine!!)

//        if ((cX != -1f) && (cY != -1f)) {
//            canvas?.drawCircle(cX, cY, 50f, paintLine!!)
//        }

        circles.forEach {
            canvas?.drawCircle(it.x, it.y, 50f, paintLine!!)
        }


        drawGrid(canvas)
    }

    private fun drawGrid(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine!!)

        canvas?.drawLine(
            0f, height.toFloat() / 3,
            width.toFloat(), height.toFloat() / 3, paintLine
        )
        canvas?.drawLine(
            0f, 2 * (height.toFloat() / 3),
            width.toFloat(), 2 * (height.toFloat() / 3), paintLine
        )

        canvas?.drawLine(
            width.toFloat() / 3, 0f,
            width.toFloat() / 3, height.toFloat(), paintLine
        )
        canvas?.drawLine(
            2 * (width.toFloat() / 3), 0f,
            2 * (width.toFloat() / 3), height.toFloat(), paintLine
        )
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //cX = event!!.x
        //cY = event!!.y

        circles.add(PointF(event!!.x, event!!.y))

        invalidate() // it will call onDraw...

        return super.onTouchEvent(event)
    }

    fun clearCircles(){
        circles.clear()

        invalidate()
    }
}