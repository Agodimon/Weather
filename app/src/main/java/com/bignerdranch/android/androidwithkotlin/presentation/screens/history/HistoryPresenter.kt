package com.bignerdranch.android.androidwithkotlin.presentation.screens.history

import com.bignerdranch.android.androidwithkotlin.presentation.base.BaseArgument
import com.bignerdranch.android.androidwithkotlin.presentation.base.BasePresenter
import com.bignerdranch.android.androidwithkotlin.presentation.base.IBasePresenter
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

interface HistoryPresenter : IBasePresenter {

    fun onClickReloadData()

    fun onBackPressed()
}

class HistoryPresenterImpl @Inject constructor(
    private val interactor: HistoryInteractor
) : BasePresenter<HistoryView>(), HistoryPresenter {

    override fun onReceiveArgument(arg: BaseArgument) {}

    override fun onViewCreated() = getAllHistory()

    override fun onClickReloadData() = getAllHistory()

    private fun getAllHistory() {
        interactor.getAllHistory()
            .doOnSubscribe { view?.get()?.showLoading() }
            .doFinally { view?.get()?.hideLoading() }
            .subscribe(
                { weather ->
                    view?.get()?.showHistoryWeather(weather)
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
