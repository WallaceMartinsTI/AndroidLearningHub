package com.wcsm.androidlearninghub.guide_handle_internet_connection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NetworkStatusViewModel(application: Application) : AndroidViewModel(application) {
    private val networkStatusTracker = NetworkStatusTracker(application)

    // Expose Network State LiveData
    val networkStatus: LiveData<CustomNetworkStatus> = networkStatusTracker.networkStatus

    init {
        // Start Network Monitoring
        networkStatusTracker.startListening()
    }

    override fun onCleared() {
        super.onCleared()
        // Stop network monitoring when ViewModel is disposed
        networkStatusTracker.stopListening()
    }
}
