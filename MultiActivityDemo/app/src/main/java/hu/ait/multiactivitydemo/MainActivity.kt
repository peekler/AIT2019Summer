package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener {
            var intentDetails = Intent(
                this@MainActivity,
                DetailsActivity::class.java
            )

            intentDetails.putExtra("KEY_DATA",
                etData.text.toString())

            //startActivity(intentDetails)
            startActivityForResult(intentDetails, 1001)

            //finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            var resultValue = data?.getIntExtra("KEY_RESULT", -1)
            btnOk.text = "OK $resultValue"
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_LONG).show()
        }
    }
}
