package com.bignerdranch.android.androidwithkotlin.di.modules

import com.bignerdranch.android.androidwithkotlin.di.scopes.FragmentScope
import com.bignerdranch.android.androidwithkotlin.presentation.screens.details.DetailsFragment
import com.bignerdranch.android.androidwithkotlin.presentation.screens.details.DetailsMvpModule
import com.bignerdranch.android.androidwithkotlin.presentation.screens.history.HistoryFragment
import com.bignerdranch.android.androidwithkotlin.presentation.screens.history.HistoryMvpModule
import com.bignerdranch.android.androidwithkotlin.presentation.screens.home.HomeFragment
import com.bignerdranch.android.androidwithkotlin.presentation.screens.home.HomeMvpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailsMvpModule::class])
    fun bindDetailsFragment(): DetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [HistoryMvpModule::class])
    fun bindHistoryFragment(): HistoryFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeMvpModule::class])
    fun bindHomeFragment(): HomeFragment
}
