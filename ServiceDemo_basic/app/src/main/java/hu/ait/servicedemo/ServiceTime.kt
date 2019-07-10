package hu.ait.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import java.util.*

class ServiceTime : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    var enabled = false

    inner class MyTimeThread : Thread() {
        override fun run() {
            val hanlderMain = Handler(Looper.getMainLooper())

            while (enabled) {
                hanlderMain.post {
                    Toast.makeText(this@ServiceTime, Date(System.currentTimeMillis()).toString(),
                        Toast.LENGTH_LONG).show()
                }

                sleep(5000)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        enabled = true
        MyTimeThread().start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        enabled = false
    }
}