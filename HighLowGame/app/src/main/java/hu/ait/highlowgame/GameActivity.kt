package hu.ait.highlowgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    var generatedNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState != null) {
            generatedNum = savedInstanceState.getInt("KEY_GEN")
        } else {
            generateRandom()
        }

        btnGuess.setOnClickListener {
            if (etGuess.text.isNotEmpty()) {
                var myNum = etGuess.text.toString().toInt()

                if (myNum == generatedNum) {
                    tvStatus.text = "Congratulations!"

                    var intentWin = Intent(
                        this@GameActivity,
                        WinActivity::class.java
                    )
                    startActivity(intentWin)

                    finish()

                } else if (myNum < generatedNum) {
                    tvStatus.text = "The generated number is larger"
                } else if (myNum > generatedNum) {
                    tvStatus.text = "The generated number is smaller"
                }
            } else {
                etGuess.error = "This field can not be empty"
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("KEY_GEN", generatedNum)

        super.onSaveInstanceState(outState)
    }


    fun generateRandom() {
        var rand = Random(System.currentTimeMillis())
        generatedNum = rand.nextInt(5)
    }
}
