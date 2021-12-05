package com.bignerdranch.android.androidwithkotlin.di

import com.bignerdranch.android.androidwithkotlin.framework.ui.details.DetailsViewModel
import com.bignerdranch.android.androidwithkotlin.framework.ui.history.HistoryViewModel
import com.bignerdranch.android.androidwithkotlin.framework.ui.list_of_cities.ListOfCitiesViewModel
import com.bignerdranch.android.androidwithkotlin.model.repository.Repository
import com.bignerdranch.android.androidwithkotlin.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
    viewModel { ListOfCitiesViewModel(get()) }
    viewModel { DetailsViewModel(get() ) }
    viewModel { HistoryViewModel(get()) }
}