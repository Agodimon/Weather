package com.bignerdranch.android.androidwithkotlin.presentation.screens.history

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.databinding.FragmentHistoryBinding
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseView
import com.bignerdranch.android.androidwithkotlin.presentation.base.IBaseView
import com.bignerdranch.android.androidwithkotlin.presentation.common.BackButtonListener
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel
import com.bignerdranch.android.androidwithkotlin.utils.hide
import com.bignerdranch.android.androidwithkotlin.utils.show
import com.bignerdranch.android.androidwithkotlin.utils.showSnackBar
import javax.inject.Inject

interface HistoryView : IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showHistoryWeather(weatherList: List<WeatherModel>)

    fun showError()
}

class HistoryFragment : BaseView<HistoryPresenter, BaseArgument>(R.layout.fragment_history),
    HistoryView, BackButtonListener {

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    @Inject
    override lateinit var presenter: HistoryPresenter

    private val historyAdapter = HistoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyFragmentRecyclerview.adapter = historyAdapter
    }

    override fun showLoading() {
        with(binding) {
            historyFragmentRecyclerview.hide()
            progressBar.show()
        }
    }

    override fun hideLoading() {
        with(binding) {
            historyFragmentRecyclerview.show()
            progressBar.hide()
        }
    }

    override fun showHistoryWeather(weatherList: List<WeatherModel>) {
        historyAdapter.setData(weatherList)
    }

    override fun showError() {
        with(binding) {
            historyFragmentRecyclerview.showSnackBar(
                text = R.string.error,
                actionText = R.string.reload,
                action = { presenter::onClickReloadData }
            )
        }
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }
}
