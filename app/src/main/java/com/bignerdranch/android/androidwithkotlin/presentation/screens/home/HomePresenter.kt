package com.bignerdranch.android.androidwithkotlin.presentation.screens.home

import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import com.bignerdranch.android.androidwithkotlin.presentation.base.BasePresenter
import com.bignerdranch.android.androidwithkotlin.presentation.base.IBasePresenter
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import com.bignerdranch.android.androidwithkotlin.utils.NavigationScreens
import javax.inject.Inject

interface HomePresenter : IBasePresenter {

    fun onLocationChanged()

    fun onClickReloadData()

    fun onItemClick(weather: WeatherModel)

    fun onBackPressed()
}

class HomePresenterImpl @Inject constructor(
    private val interactor: HomeInteractor
) : BasePresenter<HomeView>(), HomePresenter {

    private var isRusDataSet: Boolean = true

    override fun onReceiveArgument(arg: BaseArgument) {}

    override fun onViewCreated() {
        isRusDataSet = interactor.isRusLocation()
        changeFabImage()
        setData()
    }

    override fun onLocationChanged() {
        isRusDataSet = !isRusDataSet
        interactor.saveLocation(isRusDataSet)
        changeFabImage()
        setData()
    }

    private fun changeFabImage() {
        view?.get()?.setFabImage(if (isRusDataSet) R.drawable.ic_earth else R.drawable.ic_russia)
    }

    private fun setData() {
        view?.get()?.showLoading()

        val data = if (isRusDataSet) {
            interactor.getWeatherFromLocalStorageRus()
        } else {
            interactor.getWeatherFromLocalStorageWorld()
        }
        view?.get()?.showWeather(data)

        view?.get()?.hideLoading()
    }

    override fun onClickReloadData() = setData()

    override fun onItemClick(weather: WeatherModel) {
        router.navigateTo(NavigationScreens.DetailsScreen(weather.city))
    }

    override fun onBackPressed() = router.exit()
}
