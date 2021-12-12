package com.bignerdranch.android.androidwithkotlin.presentation.screens.details

import com.bignerdranch.android.androidwithkotlin.data.repositories.weather_repository.WeatherRepository
import com.bignerdranch.android.androidwithkotlin.presentation.models.CityModel
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface DetailsInteractor {

    fun getWeather(city: CityModel): Single<WeatherModel>
}

class DetailsInteractorImpl @Inject constructor(
    private val repository: WeatherRepository
) : DetailsInteractor {

    override fun getWeather(city: CityModel): Single<WeatherModel> {
        return repository.getWeather(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
