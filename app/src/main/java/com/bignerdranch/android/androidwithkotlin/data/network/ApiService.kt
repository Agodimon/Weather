package com.bignerdranch.android.androidwithkotlin.data.network

import com.bignerdranch.android.androidwithkotlin.data.network.models.WeatherBean
import com.bignerdranch.android.androidwithkotlin.data.network.utils.NetworkConstants.BASE_URL
import com.bignerdranch.android.androidwithkotlin.data.network.utils.addJsonConverter
import com.bignerdranch.android.androidwithkotlin.data.network.utils.addRxJava3Adapter
import com.bignerdranch.android.androidwithkotlin.data.network.utils.setClient
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("informers")
    fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Single<WeatherBean>

    companion object {

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addRxJava3Adapter()
                .addJsonConverter()
                .build()
                .create(ApiService::class.java)
        }
    }
}
