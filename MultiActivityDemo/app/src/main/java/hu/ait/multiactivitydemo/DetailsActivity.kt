package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent.hasExtra("KEY_DATA")){
            tvData.text = intent.getStringExtra("KEY_DATA")
        }

        btnYes.setOnClickListener {
            sendResult(1)
        }

        btnNo.setOnClickListener {
            sendResult(2)
        }
    }

    private fun sendResult(resultValue: Int) {
        var intentResult = Intent()
        intentResult.putExtra("KEY_RESULT", resultValue)
        setResult(Activity.RESULT_OK, intentResult)

        finish()
    }
}
