package com.bignerdranch.android.androidwithkotlin.framework.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.*
import com.bignerdranch.android.androidwithkotlin.databinding.FragmentHistoryBinding
import com.bignerdranch.android.androidwithkotlin.framework.ui.adapters.HistoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val viewModel: HistoryViewModel by viewModel()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        historyFragmentRecyclerview.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                historyFragmentRecyclerview.show()
                progressBar.hide()
                adapter.setData(appState.weatherData)
            }
            is AppState.Loading -> {
                historyFragmentRecyclerview.hide()
                progressBar.show()
            }
            is AppState.Error -> {
                historyFragmentRecyclerview.show()
                progressBar.hide()
                historyFragmentRecyclerview.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getAllHistory()
                    })
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }
}