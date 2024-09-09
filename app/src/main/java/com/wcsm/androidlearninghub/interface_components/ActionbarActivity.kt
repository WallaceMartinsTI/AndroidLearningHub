package com.wcsm.androidlearninghub.interface_components

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R

class ActionbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actionbar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purple)))

        inicializarActionBar()
    }

    private fun inicializarActionBar() {
        // Nova maneira de se criar Menus
        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_principal, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when(menuItem.itemId) {
                        R.id.item_adicionar -> {
                            Toast.makeText(applicationContext, "Adicionar", Toast.LENGTH_SHORT)
                                .show()
                        }

                        R.id.item_pesquisar -> {
                            Toast.makeText(applicationContext, "Pesquisar", Toast.LENGTH_SHORT)
                                .show()
                        }

                        R.id.item_configuracoes -> {
                            Toast.makeText(applicationContext, "Configurações", Toast.LENGTH_SHORT)
                                .show()
                        }

                        R.id.item_sair -> {
                            Toast.makeText(applicationContext, "Sair", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return true
                }
            }
        )
    }

    // Forma mais antiga
    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_adicionar -> {
                Toast.makeText(this, "Adicionar", Toast.LENGTH_SHORT).show()
            }
            R.id.item_pesquisar -> {
                Toast.makeText(this, "Pesquisar", Toast.LENGTH_SHORT).show()
            }
            R.id.item_configuracoes -> {
                Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
            }
            R.id.item_sair -> {
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
    */
}