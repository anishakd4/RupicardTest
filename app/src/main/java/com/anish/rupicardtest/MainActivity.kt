package com.anish.rupicardtest

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    var simpleVideoView: VideoView? = null

    // declaring a null variable for MediaController
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assigning id of VideoView from
        // activity_main.xml layout file
        simpleVideoView = findViewById<View>(R.id.simpleVideoView) as VideoView

        if (mediaControls == null) {
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }

        // set the media controller for video view
        //simpleVideoView!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        simpleVideoView!!.setVideoURI(
            Uri.parse(
                "android.resource://"
                        + packageName + "/" + R.raw.nxt_wave_bg
            )
        )

        simpleVideoView!!.requestFocus()

        // starting the video
        simpleVideoView!!.start()

        // display a toast message
        // after the video is completed
        simpleVideoView!!.setOnCompletionListener {
            Toast.makeText(
                applicationContext, "Video completed",
                Toast.LENGTH_LONG
            ).show()
        }

        // display a toast message if any
        // error occurs while playing the video
        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(
                applicationContext, "An Error Occurred " +
                        "While Playing Video !!!", Toast.LENGTH_LONG
            ).show()
            false
        }

        simpleVideoView!!.setOnPreparedListener { it.isLooping = true }

        val heroPng = findViewById<ImageView>(R.id.hero_png)

    }
}