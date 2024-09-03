package com.wcsm.androidlearninghub.recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wcsm.androidlearninghub.R

class RecyclerviewActivity : AppCompatActivity() {

    private lateinit var rvLista: RecyclerView
    private lateinit var btnExecutar: Button
    private lateinit var mensagemAdapter: MensagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recyclerview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lista = mutableListOf(
            Mensagem("Jamilton", "Olá, tudo bem?", "10:45"),
            Mensagem("Ana", "Te vi ontem...", "12:00"),
            Mensagem("Maria", "Não acredito...", "19:57"),
            Mensagem("Pedro", "Futebol hoje?", "08:12"),
            Mensagem("Jamilton", "Olá, tudo bem?", "10:45"),
            Mensagem("Ana", "Te vi ontem...", "12:00"),
            Mensagem("Maria", "Não acredito...", "19:57"),
            Mensagem("Pedro", "Futebol hoje?", "08:12"),
            Mensagem("Jamilton", "Olá, tudo bem?", "10:45"),
            Mensagem("Ana", "Te vi ontem...", "12:00"),
            Mensagem("Maria", "Não acredito...", "19:57"),
            Mensagem("Pedro", "Futebol hoje?", "08:12"),
            Mensagem("Jamilton", "Olá, tudo bem?", "10:45"),
            Mensagem("Ana", "Te vi ontem...", "12:00"),
            Mensagem("Maria", "Não acredito...", "19:57"),
            Mensagem("Pedro", "Futebol hoje?", "08:12")
        )

        rvLista = findViewById(R.id.rv_lista)
        btnExecutar = findViewById(R.id.btn_rv_executar)

        // tipo: MensagemAdapter, Adapter
        mensagemAdapter = MensagemAdapter {
            Toast.makeText(this, "RecyclerviewActivity: $it", Toast.LENGTH_SHORT).show()
        }

        mensagemAdapter.atualizarListaDados(lista)

        //rvLista.adapter = MensagemAdapter(lista) {
        rvLista.adapter = mensagemAdapter

        rvLista.layoutManager = LinearLayoutManager(this)
        //rvLista.layoutManager = GridLayoutManager(this, 2)

        //rvLista.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        btnExecutar.setOnClickListener {
            lista.add(Mensagem("Jhonatan", "Fala meu amigo...", "15:34"))
            mensagemAdapter.atualizarListaDados(lista)
        }
    }
}