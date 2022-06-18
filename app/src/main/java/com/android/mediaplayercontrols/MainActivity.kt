package com.android.mediaplayercontrols

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.mediaplayercontrols.fragment.AdAdapterCallback
import com.android.mediaplayercontrols.source.response.Ad
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView


/**
 * This Activity contains exoplayer interaction and two fragments
 * TODO exoplayer interaction can move to one more fragment
 * */
class MainActivity  : AppCompatActivity(), Player.Listener, AdAdapterCallback {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var progressBar: ProgressBar
    private var detailsFragment = false


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        setupPlayer()
        addMP4Files()
        hideDetailsFragment()
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
        addMP4Files()
    }

    private fun addMP4Files() {
        val uri = Uri.parse("asset:///krishna.mp4")
        val mediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)
        playerView.setShutterBackgroundColor(Color.TRANSPARENT)
        playerView.player = player
        player.addListener(this)
    }

    // handle loading
    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
var paused = false
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playWhenReady && playbackState == Player.STATE_READY) {
            // media actually playing
            paused = false
            hideAdFragment()
        } else if (playWhenReady) {
            // might be idle (plays after prepare()),
            // buffering (plays when data available)
            // or ended (plays when seek away from end)
            paused = false
        } else {
            // player paused in any state
                if(!paused) {
                    showAdFragment()
                    paused = true;
                }

        }
    }

    private fun hideAdFragment() {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()
        val bottomFragment: Fragment = manager.findFragmentById(R.id.fragment_container)!!
        ft.hide(bottomFragment)
        ft.commit()
    }

    private fun showAdFragment() {
        val manager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()
        val bottomFragment: Fragment = manager.findFragmentById(R.id.fragment_container)!!
        ft.show(bottomFragment)
        ft.commit()
    }

    private fun hideDetailsFragment() {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()
        val bottomFragment: Fragment = manager.findFragmentById(R.id.fragment_details_container)!!
        ft.hide(bottomFragment)
        ft.commit()
    }

    private fun showDetailsFragment() {
        val manager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()
        val bottomFragment: Fragment = manager.findFragmentById(R.id.fragment_details_container)!!
        ft.show(bottomFragment)
        ft.commit()
    }

    override fun seeAllButtonClicked() {
        detailsFragment = true
        showDetailsFragment()
    }

    override fun buyNowButtonClicked(item: Ad) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.ctafb))
        ContextCompat.startActivity(this, browserIntent, null)
    }



    override fun onBackPressed() {
        if (detailsFragment) {
            hideDetailsFragment()
            detailsFragment = false
        }
        else
            super.onBackPressed()
    }
}