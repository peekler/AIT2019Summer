package hu.ait.intentdemo

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnIntent.setOnClickListener {
            //intentSearch("Balaton")

            //intentDial()

            //intentContact()

            //intentShare()

            intentWaze()
        }
    }

    fun intentSearch(query: String) {
        val intentTest = Intent(Intent.ACTION_WEB_SEARCH)
        intentTest.putExtra(SearchManager.QUERY, query)
        startActivity(intentTest)
    }

    fun intentDial(){
        val intentDial = Intent(Intent.ACTION_DIAL,
            Uri.parse("tel:06208225581"))
        startActivity(intentDial)
    }

    fun intentContact() {
        val intentPick = Intent(Intent.ACTION_PICK,
            ContactsContract.Contacts.CONTENT_URI)
        startActivity(intentPick)
    }

    fun intentShare() {
        val intentSend = Intent(Intent.ACTION_SEND)
        intentSend.type = "text/plain"
        intentSend.setPackage("com.facebook.katana")
        intentSend.putExtra(Intent.EXTRA_TEXT, "Sharing demo")
        startActivity(intentSend)
    }

    private fun intentWaze() {
        //String wazeUri = "waze://?favorite=Home&navigate=yes";
        //val wazeUri = "waze://?ll=40.761043, -73.980545&navigate=yes"
        val wazeUri = "waze://?q=BME&navigate=yes"

        val intentTest = Intent(Intent.ACTION_VIEW)
        intentTest.data = Uri.parse(wazeUri)
        startActivity(intentTest)
    }

}
