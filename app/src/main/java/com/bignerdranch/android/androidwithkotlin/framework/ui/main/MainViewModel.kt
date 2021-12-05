package com.bignerdranch.android.androidwithkotlin.framework.ui.main

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.androidwithkotlin.AppState
import com.bignerdranch.android.androidwithkotlin.model.entities.Weather
import com.bignerdranch.android.androidwithkotlin.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)


    private fun getDataFromLocalSource(isRussian : Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)

            liveDataToObserve.postValue(
                if(isRussian){
                    AppState.Success(repository.getWeatherFromLocalStorageRus())
                } else{
                    AppState.Success(repository.getWeatherFromLocalStorageWorld())
                })
        }.start()
    }

}