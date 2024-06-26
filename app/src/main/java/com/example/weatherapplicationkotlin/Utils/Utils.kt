package com.example.weatherapplicationkotlin.Utils

import android.content.Context
import android.net.ConnectivityManager

open class Utils {
    fun isOnline(mContext: Context): Boolean {
        val conMgr = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo
        if (netInfo == null || !netInfo.isConnected || !netInfo.isAvailable) {
            return false
        }
        return true
    }
}