package com.wcsm.androidlearninghub.guide_music_player_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityMusicPlayerBinding
import com.wcsm.androidlearninghub.guide_music_player_notification.services.OnClearFromRecentService

class MusicPlayerActivity : AppCompatActivity(), Playable {

    private val binding by lazy { ActivityMusicPlayerBinding.inflate(layoutInflater) }

    private lateinit var notificationManager: NotificationManager

    private val tracks: MutableList<Track> = mutableListOf()

    private var position = 0
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        populateTracks()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()

            registerReceiver(
                broadcastReceiver,
                IntentFilter("TRACKS_TRACKS"),
                RECEIVER_NOT_EXPORTED
            )
            startService(Intent(this, OnClearFromRecentService::class.java))
        }

        binding.play.setOnClickListener {
            Log.i("#-# TESTE #-#", "Clicou no botão")
            if (isPlaying) onTrackPause() else onTrackPlay()
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CreateNotification.CHANNEL_ID,
                "KOD Dev",
                NotificationManager.IMPORTANCE_LOW
            )

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun populateTracks() {
        tracks.add(Track("Track 1", "Artist 1", R.drawable.track1))
        tracks.add(Track("Track 2", "Artist 2", R.drawable.track2))
        tracks.add(Track("Track 3", "Artist 3", R.drawable.track3))
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.extras?.getString("actionname")

            when (action) {
                CreateNotification.ACTION_PREVIOUS -> onTrackPrevious()
                CreateNotification.ACTION_PLAY -> {
                    if (isPlaying) onTrackPause() else onTrackPlay()
                }

                CreateNotification.ACTION_NEXT -> onTrackNext()
            }
        }

    }

    override fun onTrackPrevious() {
        position--
        CreateNotification().createNotification(
            this,
            tracks[position],
            R.drawable.ic_pause_24,
            position,
            tracks.size - 1
        )
        binding.title.text = (tracks[position].title)
    }

    override fun onTrackPlay() {
        CreateNotification().createNotification(
            this,
            tracks[position],
            R.drawable.ic_pause_24,
            position,
            tracks.size - 1
        )
        binding.play.setImageResource(R.drawable.ic_pause_24)
        binding.title.text = (tracks[position].title)
        isPlaying = true
    }

    override fun onTrackPause() {
        CreateNotification().createNotification(
            this,
            tracks[position],
            R.drawable.ic_play_24,
            position,
            tracks.size - 1
        )
        binding.play.setImageResource(R.drawable.ic_play_24)
        binding.title.text = (tracks[position].title)
        isPlaying = false
    }

    override fun onTrackNext() {
        position++
        CreateNotification().createNotification(
            this,
            tracks[position],
            R.drawable.ic_pause_24,
            position,
            tracks.size - 1
        )
        binding.title.text = (tracks[position].title)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.cancelAll()
        }

        unregisterReceiver(broadcastReceiver)
    }
}