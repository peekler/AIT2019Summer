package hu.ait.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.ait.tictactoe.model.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClear.setOnClickListener {
            ticTacToeView.resetGame()
        }

        var name : String? = null
        var city : String = "Budapest"

        var myCar = Car()

        myCar.type = "Tesla"
        Log.d("TAG_DEMO", myCar.type)

    }

    fun showText(text: String){
        tvStatus.text = text
    }


}
