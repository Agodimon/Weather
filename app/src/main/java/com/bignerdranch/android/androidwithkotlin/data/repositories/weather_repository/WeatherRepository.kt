package com.bignerdranch.android.androidwithkotlin.data.repositories.weather_repository

import com.bignerdranch.android.androidwithkotlin.data.database.dao.HistoryDao
import com.bignerdranch.android.androidwithkotlin.data.database.entities.HistoryEntity
import com.bignerdranch.android.androidwithkotlin.data.network.ApiService
import com.bignerdranch.android.androidwithkotlin.presentation.models.CityModel
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import com.bignerdranch.android.androidwithkotlin.utils.Preferences
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface WeatherRepository {

    fun getWeather(city: CityModel): Single<WeatherModel>

    fun getAllHistory(): Single<List<WeatherModel>>

    fun isRusLocation(): Boolean

    fun saveLocation(isRusLocation: Boolean)

    fun getWeatherFromLocalStorageRus(): List<WeatherModel>

    fun getWeatherFromLocalStorageWorld(): List<WeatherModel>
}

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val historyDao: HistoryDao,
    private val preferences: Preferences
) : WeatherRepository {

    override fun getWeather(city: CityModel): Single<WeatherModel> {
        return apiService.getWeather(lat = city.latitude, lon = city.longitude)
            .map { weatherBean ->
                val factWeather = weatherBean.factWeather
                WeatherModel(
                    temperature = factWeather.temperature ?: 0,
                    feelsLikeTemperature = factWeather.feelsLike ?: 0,
                    weatherCondition = factWeather.condition
                )
            }
            .flatMap { weather ->
                val entity = HistoryEntity(
                    0L,
                    weather.city.cityName,
                    weather.temperature ?: 0,
                    weather.weatherCondition ?: ""
                )

                historyDao.insert(entity)
                    .flatMap { Single.just(weather) }
            }
    }

    override fun getAllHistory(): Single<List<WeatherModel>> {
        return historyDao.getAllHistory()
            .map { list -> list.map { item -> item.mapToWeatherModel() } }
    }

    override fun isRusLocation(): Boolean {
        return preferences.getBoolean(Preferences.PREF_KEY_IS_RUS_LOCATION, true)
    }

    override fun saveLocation(isRusLocation: Boolean) {
        preferences.save(Preferences.PREF_KEY_IS_RUS_LOCATION, isRusLocation)
    }

    override fun getWeatherFromLocalStorageRus(): List<WeatherModel> = CityModel.getRussianCities()

    override fun getWeatherFromLocalStorageWorld(): List<WeatherModel> = CityModel.getWorldCities()
}
