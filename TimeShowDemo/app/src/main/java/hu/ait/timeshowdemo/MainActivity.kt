package hu.ait.timeshowdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnTime.setOnClickListener {

            var currentTime = Date(System.currentTimeMillis()).toString()

            Toast.makeText(
                this@MainActivity,
                currentTime,
                Toast.LENGTH_LONG
            ).show()

            tvTime.text = currentTime

            var myCar = Car("Toyota", 120, 2000)

            tvTime.text = myCar.getProperties()
        }
    }
}
