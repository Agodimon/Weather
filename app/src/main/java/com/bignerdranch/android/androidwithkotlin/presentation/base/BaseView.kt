package com.bignerdranch.android.androidwithkotlin.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

interface IBaseView

abstract class BaseView<P : IBasePresenter, A : BaseArgument>(
    @LayoutRes contentLayoutId: Int
) : DaggerFragment(contentLayoutId), IBaseView {

    abstract var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setArgument()
    }

    private fun setArgument() {
        arguments?.getParcelable<A>(BaseArgument.ARG_KEY)?.let { arg ->
            presenter.onReceiveArgument(arg)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onTakeView(this)
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        presenter.onDropView()
        super.onDestroyView()
    }

    companion object {

        fun newInstance(fragment: DaggerFragment, arg: BaseArgument? = null): DaggerFragment {
            return fragment.apply {
                arguments = Bundle().apply { putParcelable(BaseArgument.ARG_KEY, arg) }
            }
        }
    }
}
