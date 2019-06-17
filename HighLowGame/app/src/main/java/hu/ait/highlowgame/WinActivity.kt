package hu.ait.highlowgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)
    }


    override fun onBackPressed() {
        super.onBackPressed()

        //Toast.makeText(this,
        //    "You can not exit", Toast.LENGTH_LONG).show()
    }
}
