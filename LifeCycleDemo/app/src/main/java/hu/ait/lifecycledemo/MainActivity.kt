package hu.ait.lifecycledemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG_LIFE", "ONCREATE called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG_LIFE", "ONSTART called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG_LIFE", "ONRESUME called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG_LIFE", "ONPAUSE called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG_LIFE", "ONSTOP called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG_LIFE", "ONDESTROY called")
    }
}
