package com.wcsm.androidlearninghub.interface_components

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityToolbarBinding
import com.wcsm.androidlearninghub.databinding.ToolbarBinding

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

        //setSupportActionBar(binding.includeToolbar.tbPrincipal)
        inicializarToolbar()
    }

    private fun inicializarToolbar() {
        binding.btnAbrirNova.setOnClickListener {
            startActivity(Intent(this, ToolbarNovaActivity::class.java))
        }

        binding.includeToolbar.tbPrincipal.title = "Youtube"
        binding.includeToolbar.tbPrincipal.setTitleTextColor(
            ContextCompat.getColor(applicationContext, R.color.white)
        )
        binding.includeToolbar.tbPrincipal.inflateMenu(R.menu.menu_principal)
        binding.includeToolbar.tbPrincipal.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_adicionar -> {
                    Toast.makeText(applicationContext, "Adicionar", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                R.id.item_pesquisar -> {
                    Toast.makeText(applicationContext, "Pesquisar", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }

                R.id.item_configuracoes -> {
                    Toast.makeText(applicationContext, "Configurações", Toast.LENGTH_SHORT)
                        .show()
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
    }

}