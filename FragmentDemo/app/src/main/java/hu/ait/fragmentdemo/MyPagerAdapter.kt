package hu.ait.fragmentdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentMain()
            1 -> FragmentTwo()
            else -> FragmentMain()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Main page" else "Fragment two"
    }

    override fun getCount(): Int {
        return 2
    }
}