package com.example.weatherapplicationkotlin.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplicationkotlin.Repository.WeatherRepository
import com.example.weatherapplicationkotlin.model.WeatherModel

class WeatherViewModel() : ViewModel() {

    private val weatherRepository = WeatherRepository()

    fun getLiveData(cityName: String, countryName: String): MutableLiveData<WeatherModel> {
        return weatherRepository.getLiveData(cityName, countryName)
    }
}
