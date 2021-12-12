package com.bignerdranch.android.androidwithkotlin.utils

import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseView
import com.bignerdranch.android.androidwithkotlin.presentation.models.CityModel
import com.bignerdranch.android.androidwithkotlin.presentation.screens.details.DetailsFragment
import com.bignerdranch.android.androidwithkotlin.presentation.screens.history.HistoryFragment
import com.bignerdranch.android.androidwithkotlin.presentation.screens.home.HomeFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object NavigationScreens {

    fun HomeScreen() = FragmentScreen {
        BaseView.newInstance(HomeFragment())
    }

    fun HistoryScreen() = FragmentScreen {
        BaseView.newInstance(HistoryFragment())
    }

    fun DetailsScreen(city: CityModel) = FragmentScreen {
        BaseView.newInstance(DetailsFragment(), city)
    }
}
