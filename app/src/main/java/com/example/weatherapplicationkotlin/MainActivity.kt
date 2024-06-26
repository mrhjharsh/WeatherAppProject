package com.example.weatherapplicationkotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gymworkouttracker.SharedPref.SharedPref
import com.example.weatherapplicationkotlin.Utils.Utils
import com.example.weatherapplicationkotlin.ViewModel.WeatherViewModel
import com.example.weatherapplicationkotlin.databinding.ActivityMainBinding
import com.example.weatherapplicationkotlin.model.WeatherModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
        displaySavedData()
    }

    private fun initialize() {
        sharedPref = SharedPref(this)
        binding.btnCheck.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnCheck) {

            if (Utils().isOnline(this)) {
                var weatherViewModel: WeatherViewModel =
                    ViewModelProvider(this).get(WeatherViewModel::class.java)
                weatherViewModel.getLiveData(
                    binding.etCity.text.toString(),
                    binding.etCountry.text.toString()
                ).observe(this) {
                    binding.txtTemperature.text = "Temperature : " + it.temperature
                    binding.txtHumidity.text = "Humidity : " + it.humidity
                    binding.txtCondition.text = "Conditition : " + it.condition
                    binding.txtDetail.text = "Detail : " + it.description
                    saveLocalData(it)
                }

            } else {
                Toast.makeText(this, "Check your connection", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveLocalData(weatherModel: WeatherModel) {
        sharedPref.setStoredCity("City : " + binding.etCity.text.toString())
        sharedPref.setStoredTemp("Temperature : " + weatherModel.temperature)
        sharedPref.setStoredHumidity("Humidity : " + weatherModel.humidity)
        sharedPref.setStoredCondition("Condition : " + weatherModel.condition)
        sharedPref.setStoredDetail("Description " + weatherModel.description)
        displaySavedData()
    }

    private fun displaySavedData() {
        binding.txtCityStored.text = sharedPref.getStoredCity()
        binding.txtTemperatureStored.text = sharedPref.getStoredTemp()
        binding.txtHumidityStored.text = sharedPref.getStoredHumidity()
        binding.txtConditionStored.text = sharedPref.getStoredCondition()
        binding.txtDetailStored.text = sharedPref.getStoredDetail()

    }
}