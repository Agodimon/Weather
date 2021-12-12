package com.bignerdranch.android.androidwithkotlin.presentation.models

import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    val city: CityModel = CityModel.getDefaultCity(),
    val temperature: Int? = 0,
    val feelsLikeTemperature: Int? = 0,
    val weatherCondition: String? = "Неизвестно"
) : BaseArgument()
