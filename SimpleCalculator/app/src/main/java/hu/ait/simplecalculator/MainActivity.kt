package hu.ait.simplecalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlus.setOnClickListener {
            if (checkFields()) {
                try {

                    var numA = etNumA.text.toString().toInt()
                    var numB = etNumB.text.toString().toInt()

                    Log.d("TAG_MAIN", "plus button pressed $numA")

                    tvResult.text = getString(R.string.plus_result, numA, numB, numA+numB)


                } catch (e: Exception) {
                    tvResult.text = e.message
                }

            }
        }

        btnMinus.setOnClickListener {
            if (checkFields()) {
                var numA = etNumA.text.toString().toInt()
                var numB = etNumB.text.toString().toInt()

                tvResult.text = "The result is: ${numA - numB}"
            }
        }
    }

    fun checkFields() : Boolean {
        if (etNumA.text.isEmpty()) {
            etNumA.error = getString(R.string.error_number)
            return false
        }
        if (etNumB.text.isEmpty()) {
            etNumB.error = getString(R.string.error_number)
            return false
        }

        return true
    }


}
