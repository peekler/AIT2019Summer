package hu.ait.sharedpreferncesdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        val NAME_PREF = "NAME_PREF"
        val KEY_LAST_USED = "KEY_LAST_USED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvData.text = getLastUsedTime()

        saveLastTime()
    }

    fun saveLastTime() {
        var sharedPref = getSharedPreferences(
            NAME_PREF, Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putString(KEY_LAST_USED, Date(System.currentTimeMillis()).toString())
        editor.apply()
    }

    fun getLastUsedTime() : String? {
        var sharedPref = getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return sharedPref.getString(KEY_LAST_USED, "This is the first time")
    }
}
