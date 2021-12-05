package com.bignerdranch.android.androidwithkotlin.framework.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.androidwithkotlin.AppState
import com.bignerdranch.android.androidwithkotlin.model.entities.Weather
import com.bignerdranch.android.androidwithkotlin.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(cityName: String, lat: Double, lng: Double) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lng).apply {
                city.city = cityName
            }

            repository.saveEntity(data)
            liveDataToObserve.postValue(AppState.Success(listOf(data)))
        }.start()

    }


}