package com.wcsm.androidlearninghub.guide_koin.feature.presentation

import androidx.lifecycle.ViewModel
import com.wcsm.androidlearninghub.guide_koin.feature.domain.Repository

class FeatureViewModel(
    private val repository: Repository
) : ViewModel() {


    suspend fun getDate(): String {
        return repository.getData()
    }
}