package hu.ait.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentPager.adapter = MyPagerAdapter(supportFragmentManager)


        tvHello.setOnClickListener {
            //loadMainFragment()
        }
    }

    /*fun loadMainFragment() {
        var mainFragment = FragmentMain()

        var fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.fragmentContainer, mainFragment, "FragmentMain")
        fragTrans.commit()
    }*/

    public fun showText(text: String) {
        tvHello.text = text
    }
}
