package com.bignerdranch.android.androidwithkotlin.presentation.screens.details

import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import com.bignerdranch.android.androidwithkotlin.presentation.base.BasePresenter
import com.bignerdranch.android.androidwithkotlin.presentation.base.IBasePresenter
import com.bignerdranch.android.androidwithkotlin.presentation.models.CityModel
import timber.log.Timber
import javax.inject.Inject

interface DetailsPresenter : IBasePresenter {

    fun onBackPressed()
}

class DetailsPresenterImpl @Inject constructor(
    private val interactor: DetailsInteractor
) : BasePresenter<DetailsView>(), DetailsPresenter {

    var item: CityModel? = null

    override fun onReceiveArgument(arg: BaseArgument) {
        if (arg is CityModel) {
            item = arg
        }
    }

    override fun onViewCreated() {
        item?.let { city ->
            setCityDescription(city)
            getWeather(city)
        }
    }

    private fun setCityDescription(city: CityModel) {
        view?.get()?.setCityDescription(city)
    }

    private fun getWeather(city: CityModel) {
        interactor.getWeather(city)
            .doOnSubscribe { view?.get()?.showLoading() }
            .doFinally { view?.get()?.hideLoading() }
            .subscribe(
                { weather ->
                    view?.get()?.showWeather(weather)
                },
                { error ->
                    Timber.e(error)
                    view?.get()?.showError()
                }
            )
            .toDisposable()
    }

    override fun onBackPressed() = router.exit()
}
