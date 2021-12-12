package com.bignerdranch.android.androidwithkotlin.presentation.screens.main

import com.bignerdranch.android.androidwithkotlin.utils.NavigationScreens
import com.github.terrakok.cicerone.Router
import java.lang.ref.WeakReference
import javax.inject.Inject

interface MainPresenter {

    fun onTakeView(view: MainView)

    fun onResume()

    fun onDropView()

    fun onHistoryScreenClicked()
}

class MainPresenterImpl @Inject constructor() : MainPresenter {

    var view: WeakReference<MainView>? = null
    var isFirstAttach: Boolean = true

    @Inject
    lateinit var router: Router

    override fun onTakeView(view: MainView) {
        this.view = WeakReference(view)
    }

    override fun onResume() {
        if (isFirstAttach) {
            isFirstAttach = false
            router.newRootScreen(NavigationScreens.HomeScreen())
        }
    }

    override fun onDropView() {
        this.view?.clear()
    }

    override fun onHistoryScreenClicked() {
        router.navigateTo(NavigationScreens.HistoryScreen())
    }
}
