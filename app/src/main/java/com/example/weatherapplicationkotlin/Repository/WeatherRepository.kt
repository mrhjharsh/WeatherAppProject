package com.example.weatherapplicationkotlin.Repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapplicationkotlin.MyApplication
import com.example.weatherapplicationkotlin.model.WeatherModel
import org.json.JSONArray
import org.json.JSONObject


class WeatherRepository() {

    private val API_KEY = "c327c6ce036684733609975b0b276a93"

    var weatherData: MutableLiveData<WeatherModel> = MutableLiveData()

    fun fetchWeather(cityName: String, countryName: String) {

        var url: String =
            "https://api.openweathermap.org/data/2.5/weather?q=$cityName,$countryName&units=metric&appid=$API_KEY"

        val queue = Volley.newRequestQueue(MyApplication.context)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONObject(response)
                val main: JSONObject = jsonObject.getJSONObject("main")
                val weatherArray: JSONArray = jsonObject.getJSONArray("weather")
                val weatherJson: JSONObject = weatherArray.getJSONObject(0)


                var weatherModelList = WeatherModel(
                    main.getString("temp"),
                    main.getString("humidity"),
                    weatherJson.getString("main"),
                    weatherJson.getString("description")
                )

                weatherData.value = weatherModelList

            },
            {
                Toast.makeText(MyApplication.context, "Error : " + it.message, Toast.LENGTH_LONG)
                    .show();
            })
        queue.add(stringRequest)
    }

    fun getLiveData(cityName: String, countryName: String): MutableLiveData<WeatherModel> {
        fetchWeather(cityName, countryName)
        return weatherData
    }
}
