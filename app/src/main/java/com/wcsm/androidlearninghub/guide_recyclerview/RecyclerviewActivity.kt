package com.wcsm.androidlearninghub.guide_recyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityRecyclerviewBinding

class RecyclerviewActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRecyclerviewBinding.inflate(layoutInflater) }

    // Utilizado sem o viewBinding
    // private lateinit var rvLista: RecyclerView
    // private lateinit var btnExecutar: Button

    private lateinit var mensagemAdapter: MensagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //setContentView(R.layout.activity_recyclerview)
        setContentView(binding.root)

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

        // Utilizado sem o viewBinding
        // rvLista = findViewById(R.id.rv_lista)
        // btnExecutar = findViewById(R.id.btn_rv_executar)

        // tipo: MensagemAdapter, Adapter
        mensagemAdapter = MensagemAdapter {
            Toast.makeText(this, "RecyclerviewActivity: $it", Toast.LENGTH_SHORT).show()
        }

        mensagemAdapter.atualizarListaDados(lista)

        //rvLista.adapter = MensagemAdapter(lista) {
        binding.rvLista.adapter = mensagemAdapter

        binding.rvLista.layoutManager = LinearLayoutManager(this)
        //rvLista.layoutManager = GridLayoutManager(this, 2)

        //rvLista.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        binding.btnRvExecutar.setOnClickListener {
            lista.add(Mensagem("Jhonatan", "Fala meu amigo...", "15:34"))
            mensagemAdapter.atualizarListaDados(lista)
        }
    }
}