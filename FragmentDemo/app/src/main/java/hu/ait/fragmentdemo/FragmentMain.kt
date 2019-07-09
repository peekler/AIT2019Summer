package hu.ait.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*

public class FragmentMain : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(
            R.layout.fragment_main, container, false
        )

        rootView.btnDemo.setOnClickListener {
            rootView.etDemo.setText(Date(System.currentTimeMillis()).toString())

            (activity as MainActivity).showText("HELLOOOOO")
        }

        return rootView
    }

}