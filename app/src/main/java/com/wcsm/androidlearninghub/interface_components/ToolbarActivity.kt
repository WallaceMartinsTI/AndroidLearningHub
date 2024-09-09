package com.wcsm.androidlearninghub.interface_components

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityToolbarBinding

class ToolbarActivity : AppCompatActivity() {

    private val binding by lazy { ActivityToolbarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarToolbar()
    }

    private fun inicializarToolbar() {
        with(binding) {
            tbPrincipal.title = "Youtube"
            tbPrincipal.setTitleTextColor(
                ContextCompat.getColor(applicationContext, R.color.white)
            )
            tbPrincipal.inflateMenu(R.menu.menu_principal)
            tbPrincipal.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.item_adicionar -> {
                        Toast.makeText(applicationContext, "Adicionar", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.item_pesquisar -> {
                        Toast.makeText(applicationContext, "Pesquisar", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.item_configuracoes -> {
                        Toast.makeText(applicationContext, "Configurações", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener true
                    }
                    R.id.item_sair -> {
                        Toast.makeText(applicationContext, "Sair", Toast.LENGTH_SHORT).show()
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener true
                    }
                }
            }

            //setSupportActionBar(tbPrincipal)
        }
    }
}