package com.bignerdranch.android.androidwithkotlin.presentation.screens.details

import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.databinding.DetailsFragmentBinding
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseView
import com.bignerdranch.android.androidwithkotlin.presentation.base.IBaseView
import com.bignerdranch.android.androidwithkotlin.presentation.common.BackButtonListener
import com.bignerdranch.android.androidwithkotlin.presentation.models.CityModel
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import com.bignerdranch.android.androidwithkotlin.utils.hide
import com.bignerdranch.android.androidwithkotlin.utils.invisible
import com.bignerdranch.android.androidwithkotlin.utils.show
import com.squareup.picasso.Picasso
import javax.inject.Inject

interface DetailsView : IBaseView {

    fun setCityDescription(city: CityModel)

    fun showLoading()

    fun hideLoading()

    fun showWeather(weather: WeatherModel)

    fun showError()
}

class DetailsFragment : BaseView<DetailsPresenter, BaseArgument>(R.layout.details_fragment),
    DetailsView, BackButtonListener {

    private val binding by viewBinding(DetailsFragmentBinding::bind)

    @Inject
    override lateinit var presenter: DetailsPresenter

    override fun setCityDescription(city: CityModel) {
        with(binding) {
            cityName.text = city.cityName
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.latitude,
                city.longitude
            )
        }
    }

    override fun showLoading() {
        with(binding) {
            mainView.invisible()
            loadingLayout.show()
        }
    }

    override fun hideLoading() {
        with(binding) {
            loadingLayout.hide()
        }
    }

    override fun showWeather(weather: WeatherModel) {
        binding.mainView.show()
        binding.temperatureValue.text = weather.temperature?.toString() ?: "0"
        binding.feelsLikeValue.text = weather.feelsLikeTemperature?.toString() ?: "0"
        binding.weatherCondition.text = weather.weatherCondition

        Picasso.get()
            .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
            .fit()
            .into(binding.imageView)
    }

    override fun showError() {
        binding.errorTV.show()
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }
}
