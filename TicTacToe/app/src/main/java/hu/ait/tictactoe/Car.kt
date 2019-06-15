package hu.ait.tictactoe

import android.util.Log

class Car {
    var type: String? = null
        set(type) {
            Log.d("TAG_DEMO", "SET called")
            field = type
        }
        get() {
            Log.d("TAG_DEMO", "GET called")
            return field
        }
}