package com.bignerdranch.android.androidwithkotlin.model.rest

import com.bignerdranch.android.androidwithkotlin.model.rest.rest_entities.WeatherDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("informers")
    fun getWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double
    ): Call<WeatherDTO>
}