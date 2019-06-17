package hu.ait.menudemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_main){
            Toast.makeText(this, "Main was selected", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, SecondActivity::class.java))
        }
        else if (item?.itemId == R.id.action_details){
            Toast.makeText(this, "Details was selected", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, DetailsActivity::class.java))
        }
        else if (item?.itemId == R.id.action_help){
            Toast.makeText(this, "Help was selected", Toast.LENGTH_LONG).show()
        }

        return true
    }

}
