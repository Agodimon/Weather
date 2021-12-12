package com.bignerdranch.android.androidwithkotlin.presentation.base

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import java.lang.ref.WeakReference
import javax.inject.Inject

interface IBasePresenter {

    fun onReceiveArgument(arg: BaseArgument)

    fun onTakeView(view: IBaseView)

    fun onViewCreated()

    fun onDropView()
}

abstract class BasePresenter<V : IBaseView> {

    var view: WeakReference<V>? = null

    @Inject
    lateinit var router: Router

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Suppress("UNCHECKED_CAST")
    fun onTakeView(view: IBaseView) {
        this.view = WeakReference(view as? V)

        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
    }

    fun onDropView() {
        this.view?.clear()
        compositeDisposable.dispose()
    }

    protected fun Disposable.toDisposable(): Disposable = apply { addTo(compositeDisposable) }
}
