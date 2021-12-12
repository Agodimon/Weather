package com.bignerdranch.android.androidwithkotlin.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.databinding.HomeFragmentBinding
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseView
import com.bignerdranch.android.androidwithkotlin.presentation.base.IBaseView
import com.bignerdranch.android.androidwithkotlin.presentation.common.BackButtonListener
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import com.bignerdranch.android.androidwithkotlin.utils.hide
import com.bignerdranch.android.androidwithkotlin.utils.show
import com.bignerdranch.android.androidwithkotlin.utils.showSnackBar
import javax.inject.Inject

interface HomeView : IBaseView {

    fun setFabImage(@DrawableRes drawable: Int)

    fun showLoading()

    fun hideLoading()

    fun showWeather(weatherList: List<WeatherModel>)

    fun showError()
}

class HomeFragment : BaseView<HomePresenter, BaseArgument>(R.layout.home_fragment),
    HomeView, HomeFragmentAdapter.OnItemViewClickListener, BackButtonListener {

    private val binding by viewBinding(HomeFragmentBinding::bind)

    @Inject
    override lateinit var presenter: HomePresenter

    private var homeAdapter = HomeFragmentAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = homeAdapter
            mainFragmentFAB.setOnClickListener { presenter.onLocationChanged() }
        }
    }

    override fun setFabImage(drawable: Int) {
        binding.mainFragmentFAB.setImageResource(drawable)
    }

    override fun showLoading() {
        binding.mainFragmentLoadingLayout.show()
    }

    override fun hideLoading() {
        binding.mainFragmentLoadingLayout.hide()
    }

    override fun showWeather(weatherList: List<WeatherModel>) {
        homeAdapter.setWeather(weatherList)
    }

    override fun showError() {
        with(binding) {
            mainFragmentFAB.showSnackBar(
                text = R.string.error,
                actionText = R.string.reload,
                action = { presenter::onClickReloadData }
            )
        }
    }

    override fun onItemViewClick(weatherModel: WeatherModel) {
        presenter.onItemClick(weatherModel)
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }
}
