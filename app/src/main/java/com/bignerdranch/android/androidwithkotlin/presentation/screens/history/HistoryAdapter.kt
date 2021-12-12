package com.bignerdranch.android.androidwithkotlin.presentation.screens.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidwithkotlin.databinding.ItemHistoryListBinding
import com.bignerdranch.android.androidwithkotlin.presentation.models.WeatherModel


class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data: List<WeatherModel> = arrayListOf()

    fun setData(data: List<WeatherModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            ItemHistoryListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ItemHistoryListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: WeatherModel) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                recyclerViewItem.text =
                    String.format("%s %d %s", data.city.cityName, data.temperature, data.weatherCondition)
                root.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "on click: ${data.city.cityName}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }
}