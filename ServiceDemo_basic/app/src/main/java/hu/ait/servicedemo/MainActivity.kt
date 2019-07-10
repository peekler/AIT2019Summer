package hu.ait.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            var intentService = Intent(this@MainActivity, ServiceTime::class.java)
            startService(intentService)
        }

        btnStop.setOnClickListener {
            var intentService = Intent(this@MainActivity, ServiceTime::class.java)
            stopService(intentService)
        }
    }
}
