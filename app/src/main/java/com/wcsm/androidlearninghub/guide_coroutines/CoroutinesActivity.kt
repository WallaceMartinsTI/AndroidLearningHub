package com.wcsm.androidlearninghub.guide_coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

            //CoroutineScope(Dispatchers.Main).launch {
            //MainScope().launch { -> Dispatchers.Main

            //CoroutineScope(Dispatchers.IO).launch {
            //GlobalScope.launch { -> Dispatchers.IO 13058104680


            lifecycleScope.launch {
                repeat(15) { indice ->
                    //binding.btnIniciar.text = "Executando: $indice"
                    binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                    delay(1000L)
                }
            }

            /* Executa bloquenado/travando a Thread (Não recomendado)
            runBlocking {
                repeat(15) { indice ->
                    //binding.btnIniciar.text = "Executando: $indice"
                    binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                    delay(1000L)
                }
            }*/

            /*job = CoroutineScope(Dispatchers.IO).launch {

                *//*repeat(15) { indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
                    withContext(Dispatchers.Main) {
                        binding.btnIniciar.text = "Executando: $indice T: ${Thread.currentThread().name}"
                    }
                    delay(1000)

                    // Deve ser feito na Thread Main
                    // binding.btnIniciar.text = "Executou"
                }*//*

                *//*withTimeout(7000L) {
                    executar()
                }*//*

                val tempo = measureTimeMillis {
                    *//*
                    var resultado1: String? = null
                    var resultado2: String? = null

                    val job1 = launch {
                        resultado1 = tarefa1()
                    }

                    val job2 = launch {
                        resultado2 = tarefa2()
                    }

                    job1.join()
                    job2.join()
                    *//*

                    val resultado1 = async { tarefa1() }
                    val resultado2 = async { tarefa2() }

                    withContext(Dispatchers.Main) {
                        binding.btnIniciar.text = "${resultado1.await()}"
                        binding.btnParar.text = "${resultado2.await()}"
                    }

                    Log.i("info_coroutine", "resultado1: ${resultado1.await()}")
                    Log.i("info_coroutine", "resultado2: ${resultado2.await()}")
                }

                Log.i("info_coroutine", "tempo: $tempo")
            }*/
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

    private suspend fun tarefa1(): String {
        repeat(3) { indice ->
            Log.i("info_coroutine", "tarefa1: ${indice} T: ${Thread.currentThread().name}")
            delay(1000L)
        }
        return "Executou tarefa 1"
    }

    private suspend fun tarefa2(): String {
        repeat(5) { indice ->
            Log.i("info_coroutine", "tarefa2: ${indice} T: ${Thread.currentThread().name}")
            delay(1000L)
        }
        return "Executou tarefa 2"
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