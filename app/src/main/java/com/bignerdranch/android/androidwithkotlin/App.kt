package com.bignerdranch.android.androidwithkotlin

import android.app.Application
import com.bignerdranch.android.androidwithkotlin.di.DaggerAppComponent
import com.bignerdranch.android.androidwithkotlin.utils.ReleaseTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initialization()
    }

    private fun initialization() {
        initDagger()
        initTimber()
        initRxErrorHandler()
    }

    private fun initDagger() {
        DaggerAppComponent.factory()
            .create(this, applicationContext)
            .inject(this)
    }

    private fun initTimber() {
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else ReleaseTree)
    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable ->
            if (throwable is UndeliverableException) {
                Timber.e(throwable)
            }
        }
    }
}
