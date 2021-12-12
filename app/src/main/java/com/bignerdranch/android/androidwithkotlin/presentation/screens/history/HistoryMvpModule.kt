package com.bignerdranch.android.androidwithkotlin.presentation.screens.history

import com.bignerdranch.android.androidwithkotlin.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface HistoryMvpModule {

    @Binds
    @FragmentScope
    fun bindView(fragment: HistoryFragment): HistoryView

    @Binds
    @FragmentScope
    fun bindPresenter(presenter: HistoryPresenterImpl): HistoryPresenter

    @Binds
    @FragmentScope
    fun bindInteractor(interactor: HistoryInteractorImpl): HistoryInteractor
}
