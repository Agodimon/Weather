package com.bignerdranch.android.androidwithkotlin.data.network.models

import com.google.gson.annotations.SerializedName

data class WeatherBean(
    @SerializedName("fact") val factWeather: FactWeatherBean
)

data class FactWeatherBean(
    @SerializedName("temp") val temperature: Int?,
    @SerializedName("feels_like") val feelsLike: Int?,
    @SerializedName("condition") val condition: String?
)
