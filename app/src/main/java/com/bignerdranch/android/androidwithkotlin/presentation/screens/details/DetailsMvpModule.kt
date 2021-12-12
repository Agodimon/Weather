package com.bignerdranch.android.androidwithkotlin.presentation.screens.details

import com.bignerdranch.android.androidwithkotlin.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module

@Module
interface DetailsMvpModule {

    @Binds
    @FragmentScope
    fun bindView(fragment: DetailsFragment): DetailsView

    @Binds
    @FragmentScope
    fun bindPresenter(presenter: DetailsPresenterImpl): DetailsPresenter

    @Binds
    @FragmentScope
    fun bindInteractor(interactor: DetailsInteractorImpl): DetailsInteractor
}
