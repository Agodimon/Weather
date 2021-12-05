package com.bignerdranch.android.androidwithkotlin.di

import com.bignerdranch.android.androidwithkotlin.framework.ui.details.DetailsViewModel
import com.bignerdranch.android.androidwithkotlin.framework.ui.history.HistoryViewModel
import com.bignerdranch.android.androidwithkotlin.framework.ui.main.MainViewModel
import com.bignerdranch.android.androidwithkotlin.model.repository.Repository
import com.bignerdranch.android.androidwithkotlin.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get() ) }
    viewModel { HistoryViewModel(get()) }
}