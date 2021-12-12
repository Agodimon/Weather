package com.bignerdranch.android.androidwithkotlin.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bignerdranch.android.androidwithkotlin.presentation.models.CityModel
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val city: String,
    val temperature: Int,
    val condition: String
) {
    fun mapToWeatherModel() = WeatherModel(
        city = CityModel(
            cityName = city,
            latitude = 0.0,
            longitude = 0.0
        ),
        temperature = temperature,
        feelsLikeTemperature = 0,
        weatherCondition = condition
    )
}
