package com.wcsm.androidlearninghub.activityAndFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R

class ActivityAndFragmentActivity : AppCompatActivity() {

    private lateinit var buttonAbrir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_and_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonAbrir = findViewById(R.id.button_abrir)
        buttonAbrir.setOnClickListener {
            val intent = Intent(this, DetalhesActivity::class.java)

            // Passar parâmetros para nova tela
            //intent.putExtra("filme", "The Witcher")
            //intent.putExtra("classificacao", 5)
            //intent.putExtra("avaliacoes", 9.2)

            // Passando Objeto
            val filme = Filme(
                "Sem limites",
                "Teste",
                4.8,
                "Jamilton",
                "Netflix"
            )
            intent.putExtra("filme", filme)

            // Iniciar uma nova tela
            startActivity(intent)
        }

        Log.i("ciclo_vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo_vida", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo_vida", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo_vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "onStop")
    }

    override fun onDestroy() {
        Log.i("ciclo_vida", "onDestroy")
        super.onDestroy()
    }
}