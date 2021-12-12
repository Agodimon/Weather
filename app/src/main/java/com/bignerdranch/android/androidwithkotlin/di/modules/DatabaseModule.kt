package com.bignerdranch.android.androidwithkotlin.di.modules

import android.content.Context
import com.bignerdranch.android.androidwithkotlin.data.database.AppDatabase
import com.bignerdranch.android.androidwithkotlin.data.database.dao.HistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context): AppDatabase = AppDatabase.getInstance(appContext)

    @Provides
    fun provideUserDao(database: AppDatabase): HistoryDao = database.historyDao()
}
