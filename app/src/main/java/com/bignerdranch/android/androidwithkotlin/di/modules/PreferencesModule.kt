package com.bignerdranch.android.androidwithkotlin.di.modules

import com.bignerdranch.android.androidwithkotlin.utils.Preferences
import com.bignerdranch.android.androidwithkotlin.utils.PreferencesImpl
import dagger.Binds
import dagger.Module

@Module
interface PreferencesModule {

    @Binds
    fun bindPreferences(preferences: PreferencesImpl): Preferences
}
