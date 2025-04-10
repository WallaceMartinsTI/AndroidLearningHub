package com.wcsm.androidlearninghub.guide_atomic_design.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.guide_atomic_design.presentation.ui.pages.ProfilePage

class AtomicDesignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfilePage(
                name = "Wallace",
                email = "teste@teste.com"
            ) { }
        }
    }
}