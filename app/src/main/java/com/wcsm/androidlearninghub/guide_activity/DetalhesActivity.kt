package com.wcsm.androidlearninghub.guide_activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R

class DetalhesActivity : AppCompatActivity() {

    private lateinit var buttonFechar: Button
    private lateinit var textFilme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonFechar = findViewById(R.id.button_fechar)
        textFilme = findViewById(R.id.text_filme)

        val bundle = intent.extras // todos os parâmetros

        if(bundle != null) {
            //val filme = bundle.getString("filme")
            //val classificacao = bundle.getInt("classificacao")
            //val avaliacoes = bundle.getDouble("avaliacoes")
            //val resultado = "filme: $filme\nclassificação: $classificacao\navaliação: $avaliacoes"

            // SERIALIZABLE
            //val filme = bundle.getSerializable("filme") as Filme

            // PARCELABLE
            val filme = bundle.getParcelable<Filme>("filme")

            textFilme.text = "${filme?.nome} - ${filme?.distribuidor}"
        }

        buttonFechar.setOnClickListener {
            finish()
        }
    }
}