package com.bignerdranch.android.androidwithkotlin.di.modules

import com.bignerdranch.android.androidwithkotlin.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ApiService.create()
}
