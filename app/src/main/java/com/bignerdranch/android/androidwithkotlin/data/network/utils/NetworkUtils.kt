package com.bignerdranch.android.androidwithkotlin.data.network.utils

import com.bignerdranch.android.androidwithkotlin.BuildConfig
import com.bignerdranch.android.androidwithkotlin.data.network.utils.NetworkConstants.AUTH_HEADER
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun Retrofit.Builder.setClient() = apply {
    val okHttpClient = OkHttpClient.Builder()
        .addHeaderInterceptor()
        .addHttpLoggingInterceptor()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    this.client(okHttpClient)
}

private fun OkHttpClient.Builder.addHeaderInterceptor() = apply {
    val interceptor = Interceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader(AUTH_HEADER, BuildConfig.YANDEX_API_KEY)
            .build()

        chain.proceed(request)
    }

    this.addInterceptor(interceptor)
}

private fun OkHttpClient.Builder.addHttpLoggingInterceptor() = apply {
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        this.addNetworkInterceptor(interceptor)
    }
}

fun Retrofit.Builder.addJsonConverter() = apply {
    this.addConverterFactory(GsonConverterFactory.create())
}

fun Retrofit.Builder.addRxJava3Adapter() = apply {
    this.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
}
