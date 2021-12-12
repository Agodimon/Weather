package com.bignerdranch.android.androidwithkotlin.presentation.screens.main

import com.bignerdranch.android.androidwithkotlin.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module

@Module
interface MainMvpModule {

    @Binds
    @ActivityScope
    fun bindView(activity: MainActivity): MainView

    @Binds
    @ActivityScope
    fun bindPresenter(presenter: MainPresenterImpl): MainPresenter
}
