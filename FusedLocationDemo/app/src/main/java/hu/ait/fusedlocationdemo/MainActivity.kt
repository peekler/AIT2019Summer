package hu.ait.fusedlocationdemo

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var myLocationManager: MyLocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartMonitoring.isEnabled = false
        requestNeededPermission()

        myLocationManager = MyLocationManager(this)

        btnStartMonitoring.setOnClickListener {
            myLocationManager.startLocationMonitoring()
        }


        btnGeocode.setOnClickListener {
            if (prevLocation != null){
                Thread {
                    try {
                        val gc = Geocoder(this, Locale.getDefault())
                        var addrs: List<Address> =
                            gc.getFromLocation(prevLocation!!.latitude, prevLocation!!.longitude, 3)
                        val addr =
                            "${addrs[0].getAddressLine(0)}, ${addrs[0].getAddressLine(1)}," +
                                    " ${addrs[0].getAddressLine(2)}"

                        runOnUiThread {
                            Toast.makeText(this, addr, Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }.start()
            }
        }


    }

    var distance = 0.0
    var prevLocation: Location? = null

    fun showLocation(newLoc: Location) {

        if (prevLocation != null && newLoc.accuracy < 14
            && (System.currentTimeMillis() - newLoc.time > 5000 )) {
            distance = distance + (newLoc.distanceTo(prevLocation))
        }

        prevLocation = newLoc

        var locationInfo =
                """
                    Provider: ${newLoc.provider}
                    Latitude: ${newLoc.latitude}
                    Longitude: ${newLoc.longitude}
                    Accuracy: ${newLoc.accuracy}
                    Time: ${Date(newLoc.time).toString()}
                    Altitude: ${newLoc.altitude}
                    Speed: ${newLoc.speed}

                    Distance: $distance
                """.trimIndent()

        tvLocation.text = locationInfo
    }

    override fun onStop() {
        super.onStop()
        myLocationManager.stopLocationMonitoring()
    }

    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this,
                    "I need it for location", Toast.LENGTH_SHORT).show()
            }

            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101)
        } else {
            // we already have permission
            btnStartMonitoring.isEnabled = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "LOCATION perm granted", Toast.LENGTH_SHORT).show()
                    btnStartMonitoring.isEnabled = true
                } else {
                    Toast.makeText(this, "LOCATION perm NOT granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
