package com.wcsm.androidlearninghub.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class CoroutinesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutinesBinding.inflate(layoutInflater) }

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnIniciar.setOnClickListener {
            job = CoroutineScope(Dispatchers.IO).launch {

                /*repeat(15) { indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
                    withContext(Dispatchers.Main) {
                        binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                    }
                    delay(1000)

                    // Deve ser feito na Thread Main
                    // binding.btnIniciar.text = "Executou"
                }*/

                withTimeout(7000L) {
                    executar()
                }
            }
        }

        binding.btnParar.setOnClickListener {
            job?.cancel()
            binding.btnIniciar.text = "Reiniciar execução"
            binding.btnIniciar.isEnabled = true
        }
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    private suspend fun executar() {
        repeat(15) { indice ->
            Log.i("info_coroutine", "Executando: ${indice} T: ${Thread.currentThread().name}")

            withContext(Dispatchers.Main) {
                binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                binding.btnIniciar.isEnabled = false
            }

            delay(1000L)
        }
    }

    private suspend fun dadosUsuario() {
        val usuario = recuperarUsuarioLogado()
        Log.i("info_coroutine", "usuario: ${usuario} T: ${Thread.currentThread().name}")
        val postagens = recuperarPostagensPeloId(usuario.id)
        Log.i("info_coroutine", "postagens: ${postagens} T: ${Thread.currentThread().name}")
    }

    private suspend fun recuperarPostagensPeloId(idUsuario: Int): List<String> {
        delay(2000) // Simula a demora ao buscar as postagens
        return listOf("Viagem Nordeste", "Estudando Android", "Jantando Restaurante")
    }

    private suspend fun recuperarUsuarioLogado(): Usuario {
        delay(2000) // Simula demora ao buscar dado na Web
        return Usuario(id = 1020, nome = "Jamilton Damasceno")
    }
}