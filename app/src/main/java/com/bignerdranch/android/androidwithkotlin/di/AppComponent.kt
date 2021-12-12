package com.bignerdranch.android.androidwithkotlin.di

import android.content.Context
import com.bignerdranch.android.androidwithkotlin.App
import com.bignerdranch.android.androidwithkotlin.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        NavigationModule::class,
        RepositoryModule::class,
        PreferencesModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: App,
            @BindsInstance appContext: Context
        ): AppComponent
    }

    fun inject(application: App)
}
