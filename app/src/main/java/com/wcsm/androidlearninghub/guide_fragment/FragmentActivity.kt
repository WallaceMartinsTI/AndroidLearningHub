package com.wcsm.androidlearninghub.guide_fragment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.guide_fragment.fragments.ChamadasFragment
import com.wcsm.androidlearninghub.guide_fragment.fragments.ConversasFragment

class FragmentActivity : AppCompatActivity() {

    private lateinit var btnMercado: Button
    private lateinit var btnChamadas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)

        Log.i("ciclo_vida", "Activity onCreate")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnMercado = findViewById(R.id.btn_mercado)
        btnChamadas = findViewById(R.id.btn_chamadas)

        //val fragmentManager = supportFragmentManager.beginTransaction()
        // Diversas alterações em fragments
        //fragmentManager.add(R.id.fragment_conteudo, ConversasFragment())
        //fragmentManager.commit()

        //val conversasFragment = ConversasFragment() -> Para usar o remove, é necessário uma instância
        btnMercado.setOnClickListener {
            /* Forma Convencional
            val conversasFragment = ConversasFragment()

            val bundle = bundleOf(
                "categoria" to "MERCADO",
                "usuario" to "Jamilton"
            )

            conversasFragment.arguments = bundle

            // Forma mais compacta
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conteudo, conversasFragment)
                .commit()
             */

            // Utilizando o Fragment KTX
            val bundle = bundleOf(
                "categoria" to "MERCADO",
                "usuario" to "Jamilton"
            )

            supportFragmentManager.commit {
                replace<ConversasFragment>(R.id.fragment_conteudo, args = bundle)
            }
        }

        btnChamadas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conteudo, ChamadasFragment())
                //.remove(conversasFragment)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo_vida", "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo_vida", "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "Activity onStop")
    }

    override fun onDestroy() {
        Log.i("ciclo_vida", "Activity onDestroy")
        super.onDestroy()
    }
}