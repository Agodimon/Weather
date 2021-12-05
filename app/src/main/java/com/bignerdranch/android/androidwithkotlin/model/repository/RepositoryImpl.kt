package com.bignerdranch.android.androidwithkotlin.model.repository

import com.bignerdranch.android.androidwithkotlin.model.WeatherLoader
import com.bignerdranch.android.androidwithkotlin.model.database.Database
import com.bignerdranch.android.androidwithkotlin.model.database.HistoryEntity
import com.bignerdranch.android.androidwithkotlin.model.entities.City
import com.bignerdranch.android.androidwithkotlin.model.entities.Weather
import com.bignerdranch.android.androidwithkotlin.model.rest.WeatherRepo

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lng: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat, lng).execute().body()
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()

    override fun getAllHistory(): List<Weather> =
        convertHistoryEntityToWeather(Database.db.historyDao().all())


    override fun saveEntity(weather: Weather) {
        Database.db.historyDao().insert(convertWeatherToEntity(weather))
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> =
        entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }

    private fun convertWeatherToEntity(weather: Weather): HistoryEntity =
        HistoryEntity(
            0, weather.city.city,
            weather.temperature ?: 0,
            weather.condition ?: ""
        )

}