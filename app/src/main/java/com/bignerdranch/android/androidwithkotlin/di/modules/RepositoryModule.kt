package com.bignerdranch.android.androidwithkotlin.di.modules

import com.bignerdranch.android.androidwithkotlin.data.repositories.weather_repository.WeatherRepository
import com.bignerdranch.android.androidwithkotlin.data.repositories.weather_repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository
}
