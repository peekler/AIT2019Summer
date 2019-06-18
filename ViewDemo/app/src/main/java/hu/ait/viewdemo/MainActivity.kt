package hu.ait.viewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val cityNames = arrayOf(
        "Budapest", "Bukarest", "New York", "New Delhi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityAdapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line,
            cityNames)
        autoCity.setAdapter(cityAdapter)


        val fruitsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.fruits_array,
            android.R.layout.simple_spinner_item
        )
        fruitsAdapter.setDropDownViewResource(
            android.R.layout.simple_dropdown_item_1line
        )
        spinnerFruits.adapter = fruitsAdapter


        btnSave.setOnClickListener {
            var fruit = spinnerFruits.selectedItem.toString()

            Toast.makeText(this@MainActivity, fruit, Toast.LENGTH_LONG).show()
        }
    }
}
