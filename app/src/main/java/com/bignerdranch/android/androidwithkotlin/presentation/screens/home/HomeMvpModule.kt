package com.bignerdranch.android.androidwithkotlin.presentation.screens.home

import com.bignerdranch.android.androidwithkotlin.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface HomeMvpModule {

    @Binds
    @FragmentScope
    fun bindView(fragment: HomeFragment): HomeView

    @Binds
    @FragmentScope
    fun bindPresenter(presenter: HomePresenterImpl): HomePresenter

    @Binds
    @FragmentScope
    fun bindInteractor(interactor: HomeInteractorImpl): HomeInteractor
}
