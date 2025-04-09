package com.wcsm.androidlearninghub.guide_koin.core.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wcsm.androidlearninghub.guide_koin.feature.presentation.FeatureScreen
import org.koin.androidx.compose.koinViewModel

class KoinActivity : AppCompatActivity() {

    //val viewModel by viewModels<KoinViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: KoinViewModel = koinViewModel()
            FeatureScreen()
        }
    }

}