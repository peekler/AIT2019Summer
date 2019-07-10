package hu.ait.sensordemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnList.setOnClickListener {
            val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

            tvData.text=""
            sensorManager.getSensorList(Sensor.TYPE_ALL).forEach {
                tvData.append("${it.name}\n")
            }
        }

        btnStart.setOnClickListener {
            val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            val accelero = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            sensorManager.registerListener(this, accelero, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        tvData.text = """
            X: ${p0!!.values[0]}
            Y: ${p0!!.values[1]}
            Z: ${p0!!.values[2]}
        """.trimIndent()
    }
}
