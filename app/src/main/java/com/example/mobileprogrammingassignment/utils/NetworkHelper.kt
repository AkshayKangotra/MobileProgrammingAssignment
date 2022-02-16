package com.example.mobileprogrammingassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import com.example.mobileprogrammingassignment.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(@ApplicationContext private val context:  Context) {

    fun isNetworkConnected(): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    fun isNetworkAvailable() :Boolean{
        if(isNetworkConnected()) return true
        else {
            Toast.makeText(
                context,
                context.getString(R.string.err_no_network_available),
                Toast.LENGTH_LONG
            ).show()
            return false
        }
    }
}