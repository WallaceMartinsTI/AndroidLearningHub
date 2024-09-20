package com.wcsm.androidlearninghub.guide_notification

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNotificationBinding.inflate(layoutInflater) }

    private var temPermissaoNotificacao = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        var builder = NotificationCompat.Builder(this, "canalLembrete")
            .setSmallIcon(R.drawable.ic_notification_24)
            .setContentTitle("Lembrete")
            .setContentText("Essa é a notificação de lembrete!")
            // Quando clicar na notificação vai chamar a pendingIntent
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        binding.btnShowNotification.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    solicitarPermissao()
                }
                return@setOnClickListener
            }
            NotificationManagerCompat.from(this).notify(1, builder.build())
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun solicitarPermissao() {
        // Verificar se o usuário tem a permissao
        temPermissaoNotificacao = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        val permissoesNegadas = mutableListOf<String>()

        if(!temPermissaoNotificacao) {
            permissoesNegadas.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        if(permissoesNegadas.isNotEmpty()) {
            // Solicitar Permissão
            val gerenciadorPermissoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissao ->
                temPermissaoNotificacao = permissao[Manifest.permission.POST_NOTIFICATIONS] ?: temPermissaoNotificacao
            }
            gerenciadorPermissoes.launch(permissoesNegadas.toTypedArray())
        }
    }
}