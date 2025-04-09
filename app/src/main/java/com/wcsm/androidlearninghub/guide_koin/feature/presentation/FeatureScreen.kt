package com.wcsm.androidlearninghub.guide_koin.feature.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        println("we got: ${viewModel.getDate()}")
    }

}