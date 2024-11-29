package com.wcsm.androidlearninghub.guide_music_player_notification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.wcsm.androidlearninghub.R

class CreateNotification {

    companion object {
        const val CHANNEL_ID = "channel1"

        const val ACTIONPREVIOUS = "actionprevious"
        const val ACTIONPLAY = "actionplay"
        const val ACTIONNEXT = "actionnext"
    }

    fun createNotification(context: Context, track: Track, playbutton: Int, pos: Int, size: Int) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            val mediaSessionCompat = MediaSessionCompat(context, "tag")

            val icon = BitmapFactory.decodeResource(context.resources, track.image)

            // Create Notification
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music_24)
                .setContentTitle(track.title)
                .setContentText(track.artist)
                .setLargeIcon(icon)
                .setOnlyAlertOnce(true) // Show notification for only first time
                .setShowWhen(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificationManagerCompat.notify(1, notification)
        }
    }

}