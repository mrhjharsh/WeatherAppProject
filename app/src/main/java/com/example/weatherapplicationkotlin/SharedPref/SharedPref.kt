package com.example.gymworkouttracker.SharedPref

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapplicationkotlin.R

class SharedPref(mActivity: Activity) {

    private var sharedPreferences: SharedPreferences = mActivity.getSharedPreferences(
        mActivity.resources.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    private var activity: Activity = mActivity

    var STORED_CITY = "stored_city"
    var STORED_TEMP = "stored_temperature"
    var STORED_HUMI = "stored_humidity"
    var STORED_CONDI = "stored_condition"
    var STORED_DETAIL = "stored_detail"


    fun setStoredCity(value: String) {
        editor.putString(STORED_CITY, value)
        editor.commit()
    }

    fun getStoredCity(): String? {
        return sharedPreferences.getString(STORED_CITY, "")
    }

    fun setStoredTemp(value: String) {
        editor.putString(STORED_TEMP, value)
        editor.commit()
    }

    fun getStoredTemp(): String? {
        return sharedPreferences.getString(STORED_TEMP, "")
    }

    fun setStoredHumidity(value: String) {
        editor.putString(STORED_HUMI, value)
        editor.commit()
    }

    fun getStoredHumidity(): String? {
        return sharedPreferences.getString(STORED_HUMI, "")
    }

    fun setStoredCondition(value: String) {
        editor.putString(STORED_CONDI, value)
        editor.commit()
    }

    fun getStoredCondition(): String? {
        return sharedPreferences.getString(STORED_CONDI, "")
    }

    fun setStoredDetail(value: String) {
        editor.putString(STORED_DETAIL, value)
        editor.commit()
    }

    fun getStoredDetail(): String? {
        return sharedPreferences.getString(STORED_DETAIL, "")
    }
}