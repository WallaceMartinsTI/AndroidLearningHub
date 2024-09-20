package com.wcsm.androidlearninghub

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        criarCanalNotificacao()
    }

    private fun criarCanalNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                "canalLembrete",
                "Canal de Lembrete",
                NotificationManager.IMPORTANCE_HIGH
            )
            canal.description = "Canal para gerênciar as notificações de lembrete!"

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canal)
        }
    }

}