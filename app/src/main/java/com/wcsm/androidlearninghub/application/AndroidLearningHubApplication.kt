package com.wcsm.androidlearninghub.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.wcsm.androidlearninghub.guide_koin.core.di.coreModule
import com.wcsm.androidlearninghub.guide_koin.feature.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidLearningHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin Guide
        startKoin {
            androidContext(this@AndroidLearningHubApplication)
            modules(
                coreModule,
                featureModule
            )
        }

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