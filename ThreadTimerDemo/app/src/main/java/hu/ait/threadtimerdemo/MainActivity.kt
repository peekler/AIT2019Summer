package hu.ait.threadtimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var enabled = false

    inner class MyThread : Thread() {
        override fun run() {
            while (enabled) {
                runOnUiThread {
                    tvData.append("#")
                }

                sleep(1000)
            }
        }
    }


    inner class MyTimerTask : TimerTask(){
        override fun run() {
            runOnUiThread{
                tvData.append("&")
            }
        }
    }

    var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            enabled = true
            MyThread().start()

            tvData.append("hello")

            timer = Timer()
            timer?.schedule(MyTimerTask(), 0, 500)
        }

        btnStop.setOnClickListener {
            enabled = false

            timer?.cancel()
        }

    }

    override fun onStop() {
        super.onStop()
        enabled = false

        timer?.cancel()
    }
}
