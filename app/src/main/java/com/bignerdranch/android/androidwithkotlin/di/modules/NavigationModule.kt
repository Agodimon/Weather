package com.bignerdranch.android.androidwithkotlin.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideAppRouter(): Router = cicerone.router

    @Provides
    @Singleton
    fun provideAppNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
}
