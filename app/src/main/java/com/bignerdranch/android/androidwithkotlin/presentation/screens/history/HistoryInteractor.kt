package com.bignerdranch.android.androidwithkotlin.presentation.screens.history

import com.bignerdranch.android.androidwithkotlin.data.repositories.weather_repository.WeatherRepository
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface HistoryInteractor {

    fun getAllHistory(): Single<List<WeatherModel>>
}

class HistoryInteractorImpl @Inject constructor(
    private val repository: WeatherRepository
) : HistoryInteractor {

    override fun getAllHistory(): Single<List<WeatherModel>> {
        return repository.getAllHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
