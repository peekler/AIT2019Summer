package hu.ait.musicdemo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer =
            MediaPlayer.create(this@MainActivity, R.raw.demo)
        mediaPlayer.setOnPreparedListener(this)
        btnPlay.isEnabled = false


        btnPlay.setOnClickListener {
            mediaPlayer.start()
        }

        btnStop.setOnClickListener {
            //mediaPlayer.seekTo(60000)
            mediaPlayer.stop()
        }

    }

    override fun onPrepared(mediaPlayer: MediaPlayer?) {
        btnPlay.isEnabled = true
    }

    override fun onStop() {
        super.onStop()

        try {
            mediaPlayer.stop()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
