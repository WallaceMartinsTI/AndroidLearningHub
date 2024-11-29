package com.wcsm.androidlearninghub.guide_music_player_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityMusicPlayerBinding

class MusicPlayerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMusicPlayerBinding.inflate(layoutInflater) }

    private val tracks: MutableList<Track> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        populateTracks()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        binding.play.setOnClickListener {
            CreateNotification().createNotification(
                this,
                tracks[1],
                R.drawable.ic_pause_24,
                1,
                tracks.size - 1
            )
        }
    }

    private fun createChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
}