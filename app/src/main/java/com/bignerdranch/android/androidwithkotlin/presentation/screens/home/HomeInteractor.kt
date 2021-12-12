package com.bignerdranch.android.androidwithkotlin.presentation.screens.home

import com.bignerdranch.android.androidwithkotlin.data.repositories.weather_repository.WeatherRepository
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import javax.inject.Inject

interface HomeInteractor {

    fun isRusLocation(): Boolean

    fun saveLocation(isRusLocation: Boolean)

    fun getWeatherFromLocalStorageRus(): List<WeatherModel>

    fun getWeatherFromLocalStorageWorld(): List<WeatherModel>
}

class HomeInteractorImpl @Inject constructor(
    private val repository: WeatherRepository
) : HomeInteractor {

    override fun isRusLocation(): Boolean {
        return repository.isRusLocation()
    }

    override fun saveLocation(isRusLocation: Boolean) {
        repository.saveLocation(isRusLocation)
    }

    override fun getWeatherFromLocalStorageRus(): List<WeatherModel> {
        return repository.getWeatherFromLocalStorageRus()
    }

    override fun getWeatherFromLocalStorageWorld(): List<WeatherModel> {
        return repository.getWeatherFromLocalStorageWorld()
    }
}
