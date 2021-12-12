package com.bignerdranch.android.androidwithkotlin.di.modules

import com.bignerdranch.android.androidwithkotlin.di.scopes.ActivityScope
import com.bignerdranch.android.androidwithkotlin.presentation.screens.main.MainActivity
import com.bignerdranch.android.androidwithkotlin.presentation.screens.main.MainMvpModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            AndroidInjectionModule::class,
            FragmentModule::class,
            MainMvpModule::class
        ]
    )
    fun bindMainActivity(): MainActivity
}
