package hu.ait.tictactoe.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import hu.ait.tictactoe.MainActivity
import hu.ait.tictactoe.R
import hu.ait.tictactoe.model.TicTacToeModel

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackGround : Paint? = null
    var paintLine : Paint? = null

    var bitmapBg = BitmapFactory.decodeResource(
        resources, R.drawable.grass
    )

    init {
        paintBackGround = Paint()
        paintBackGround?.color = Color.BLACK
        paintBackGround?.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine?.color = Color.WHITE
        paintLine?.style = Paint.Style.STROKE
        paintLine?.strokeWidth = 5f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg, width/3, height/3, false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f,
            width.toFloat(), height.toFloat(), paintBackGround!!)

        canvas?.drawBitmap(bitmapBg, 0f, 0f, null)

        canvas?.drawBitmap(bitmapBg, width/3f, 0f, null)


        drawGrid(canvas)

        drawPlayers(canvas)
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

    private fun drawPlayers(canvas: Canvas?) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas?.drawCircle(centerX, centerY, radius.toFloat(), paintLine!!)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas?.drawLine((i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLine!!)

                    canvas?.drawLine(((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine!!)
                }
            }
        }
    }




    override fun onTouchEvent(event: MotionEvent): Boolean {
        val tX = event.x.toInt() / (width / 3)
        val tY = event.y.toInt() / (height / 3)

        if (tX<3 && tY<3 && TicTacToeModel.getFieldContent(tX, tY)==TicTacToeModel.EMPTY) {
            TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.nextPlayer)
            TicTacToeModel.changeNextPlayer()

            var nextText = if (TicTacToeModel.nextPlayer == TicTacToeModel.CIRCLE) {
                "O is the next player"
            } else {
                "X is the next player"
            }

            (context as MainActivity).showText(nextText)


            invalidate()
        }

        return super.onTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

    fun resetGame(){
        TicTacToeModel.resetModel()
        invalidate()
    }
}