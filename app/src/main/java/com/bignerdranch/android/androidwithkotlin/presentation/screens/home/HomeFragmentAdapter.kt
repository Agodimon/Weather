package com.bignerdranch.android.androidwithkotlin.presentation.screens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidwithkotlin.databinding.FragmentMainRecyclerItemBinding
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel

class HomeFragmentAdapter(private var itemClickListener: OnItemViewClickListener) :
    RecyclerView.Adapter<HomeFragmentAdapter.HomeViewHolder>() {
    private var weatherModelData: List<WeatherModel> = listOf()
    private lateinit var binding: FragmentMainRecyclerItemBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setWeather(data: List<WeatherModel>) {
        weatherModelData = data
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weatherModel: WeatherModel) = with(binding) {
            mainFragmentRecyclerItemTextView.text = weatherModel.city.cityName
            root.setOnClickListener { itemClickListener.onItemViewClick(weatherModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(weatherModelData[position])
    }

    override fun getItemCount() = weatherModelData.size

    interface OnItemViewClickListener {

        fun onItemViewClick(weatherModel: WeatherModel)
    }
}
