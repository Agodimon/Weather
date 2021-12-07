package com.bignerdranch.android.androidwithkotlin.framework.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.*
import com.bignerdranch.android.androidwithkotlin.databinding.DetailsFragmentBinding
import com.bignerdranch.android.androidwithkotlin.databinding.MainFragmentBinding
import com.bignerdranch.android.androidwithkotlin.model.entities.Weather
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by viewModel()
    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
        weather?.let {
            with(binding) {
                cityName.text = it.city.city
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    it.city.lat.toString(),
                    it.city.lon.toString()
                )
                viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            mainView.invisible()
                            loadingLayout.hide()
                            errorTV.show()
                        }
                        AppState.Loading -> {
                            mainView.invisible()
                            binding.loadingLayout.show()
                        }
                        is AppState.Success -> {
                            loadingLayout.hide()
                            mainView.show()
                            temperatureValue.text = appState.weatherData[0].temperature.toString()
                            feelsLikeValue.text = appState.weatherData[0].feelsLike.toString()
                            weatherCondition.text = appState.weatherData[0].condition
                        }
                    }
                })
                viewModel.loadData(it.city.city, it.city.lat, it.city.lon)

                Picasso
                    .get()
                    .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                    .fit()
                    .into(imageView)
            }
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}