package com.wcsm.androidlearninghub.guide_handle_internet_connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wcsm.androidlearninghub.R
import com.wcsm.androidlearninghub.databinding.ActivityHandleInternetConnectionBinding

class HandleInternetConnectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHandleInternetConnectionBinding.inflate(layoutInflater) }

    private val networkStatusViewModel: NetworkStatusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Live-update with NetworkCallback (MUCH BETTER)
        networkStatusViewModel.networkStatus.observe(this) { status ->
            when(status) {
                CustomNetworkStatus.AVAILABLE -> {
                    // Connected to the internet
                    binding.textAutoConnection.text = "AVAILABLE (Conectado)"
                    showToastMessage(this, "CONECTADO A INTERNET!")
                }
                CustomNetworkStatus.LOST -> {
                    binding.textAutoConnection.text = "LOST (Perdida)"
                    showToastMessage(this, "CONEXÃO PERDIDA!")
                }
                CustomNetworkStatus.UNAVAILABLE -> {
                    binding.textAutoConnection.text = "UNAVAILABLE (Indisponível)"
                    showToastMessage(this, "CONEXÃO INDISPONÍVEL!")
                }
            }
        }

        // Check internet connection
        binding.btnCheckInternetConnection.setOnClickListener {
            val hasInternet = isInternetAvailable(this)
            binding.textBtnConnection.text = if(hasInternet) {
                "Internet Disponível"
            } else {
                "Internet Indisponível!"
            }
        }
    }


    // Call this function to check internet availability
    private fun isInternetAvailable(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        return networkCapabilities?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        ) == true
    }

    private fun showToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}