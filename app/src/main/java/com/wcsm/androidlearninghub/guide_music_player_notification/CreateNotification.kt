package com.wcsm.androidlearninghub.guide_music_player_notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.guide_music_player_notification.services.NotificationActionService

class CreateNotification {

    companion object {
        const val CHANNEL_ID = "channel1"

        const val ACTION_PREVIOUS = "actionprevious"
        const val ACTION_PLAY = "actionplay"
        const val ACTION_NEXT = "actionnext"
    }

    fun createNotification(context: Context, track: Track, playbutton: Int, pos: Int, size: Int) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            val mediaSessionCompat = MediaSessionCompat(context, "tag")

            val icon = BitmapFactory.decodeResource(context.resources, track.image)

            val pendingIntentPrevious: PendingIntent?
            val pendingIntentPlay: PendingIntent?
            val intentPrevious: Intent?
            val intentPlay: Intent?
            val drwPrevious: Int

            if(pos == 0) {
                pendingIntentPrevious = null
                drwPrevious = 0
            } else {
                intentPrevious = Intent(context, NotificationActionService::class.java).setAction(
                    ACTION_PREVIOUS
                )
                pendingIntentPrevious = PendingIntent.getBroadcast(
                    context,
                    0,
                    intentPrevious,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                drwPrevious = R.drawable.ic_previous_24
            }

            intentPlay = Intent(context, NotificationActionService::class.java).setAction(
                ACTION_PLAY
            )
            pendingIntentPlay = PendingIntent.getBroadcast(
                context,
                0,
                intentPlay,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val pendingIntentNext: PendingIntent?
            val intentNext: Intent?
            val drwNext: Int

            if(pos == size) {
                pendingIntentNext = null
                drwNext = 0
            } else {
                intentNext = Intent(context, NotificationActionService::class.java).setAction(
                    ACTION_NEXT
                )
                pendingIntentNext = PendingIntent.getBroadcast(
                    context,
                    0,
                    intentNext,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                drwNext = R.drawable.ic_next_24
            }

            // Create Notification
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music_24)
                .setContentTitle(track.title)
                .setContentText(track.artist)
                .setLargeIcon(icon)
                .setOnlyAlertOnce(true) // Show notification for only first time
                .setShowWhen(false)
                .addAction(drwPrevious, "Previous", pendingIntentPrevious)
                .addAction(playbutton, "Play", pendingIntentPlay)
                .addAction(drwNext, "Next", pendingIntentNext)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                        .setMediaSession(mediaSessionCompat.sessionToken)
                )
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