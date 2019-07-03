package hu.ait.mapdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnNormal.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        btnSatellite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val hungary = LatLng(47.0, 19.0)
        mMap.addMarker(MarkerOptions().position(hungary).title("Marker in Hungary"))


        mMap.moveCamera(CameraUpdateFactory.newLatLng(hungary))

        mMap.isTrafficEnabled = true

        mMap.uiSettings.isZoomControlsEnabled = true


        val markerDemo = mMap.addMarker(
            MarkerOptions()
                .position(LatLng(40.0, 18.0))
                .title("Demo")
                .snippet("Population: 9.700.000"))
        markerDemo.isDraggable = true


        mMap.setOnMapClickListener {
            val markerClick = mMap.addMarker(
                    MarkerOptions()
                        .position(it)
                        .title("Clicked marker"))


            val random = Random(System.currentTimeMillis())
            val cameraPostion = CameraPosition.Builder()
                .target(it)
                .zoom(10f+random.nextInt(5))
                .tilt(30f+random.nextInt(15))
                .bearing(45f+random.nextInt(45))
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPostion))
        }




    }
}
