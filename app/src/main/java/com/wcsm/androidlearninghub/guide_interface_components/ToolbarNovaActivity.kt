package com.wcsm.androidlearninghub.guide_interface_components

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityToolbarNovaBinding

class ToolbarNovaActivity : AppCompatActivity() {

    private val binding by lazy { ActivityToolbarNovaBinding.inflate(layoutInflater) }

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
        binding.includeToolbar.clLogo.visibility = View.GONE
        binding.includeToolbar.tbPrincipal.title = "Upload Vídeo"
        setSupportActionBar(binding.includeToolbar.tbPrincipal)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}