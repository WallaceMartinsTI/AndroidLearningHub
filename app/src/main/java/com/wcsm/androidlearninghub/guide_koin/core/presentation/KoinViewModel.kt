package com.wcsm.androidlearninghub.guide_koin.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcsm.androidlearninghub.guide_koin.feature.domain.Repository
import kotlinx.coroutines.launch

class KoinViewModel(
    private val repository: Repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            println("we got 2: ${repository.getData()}")
        }
    }
}