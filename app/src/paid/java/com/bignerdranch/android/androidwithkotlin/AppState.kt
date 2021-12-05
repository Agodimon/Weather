package com.bignerdranch.android.androidwithkotlin

import com.bignerdranch.android.androidwithkotlin.model.entities.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
