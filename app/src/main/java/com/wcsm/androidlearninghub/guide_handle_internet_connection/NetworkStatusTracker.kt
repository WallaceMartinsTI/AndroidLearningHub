package com.wcsm.androidlearninghub.guide_handle_internet_connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NetworkStatusTracker(context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Exposed LiveData for the connection status
    private val _networkStatus = MutableLiveData<CustomNetworkStatus>()
    val networkStatus: LiveData<CustomNetworkStatus> get() = _networkStatus

    // NetworkCallback to listen to network changes
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _networkStatus.postValue(CustomNetworkStatus.AVAILABLE)
        }

        override fun onLost(network: Network) {
            _networkStatus.postValue(CustomNetworkStatus.LOST)
        }

        override fun onUnavailable() {
            _networkStatus.postValue(CustomNetworkStatus.UNAVAILABLE)
        }
    }

    // Register the callback to start monitoring connectivity
    fun startListening() {
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        _networkStatus.value = when {
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true -> {
                CustomNetworkStatus.AVAILABLE
            }
            else -> CustomNetworkStatus.UNAVAILABLE
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    // Unregister the callback when no longer needed
    fun stopListening() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}