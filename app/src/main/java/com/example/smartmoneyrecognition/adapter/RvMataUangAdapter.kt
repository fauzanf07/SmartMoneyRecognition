package com.example.smartmoneyrecognition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmoneyrecognition.data_class.rvMataUang
import com.example.smartmoneyrecognition.databinding.RvMataUangBinding

class RvMataUangAdapter (
    private val listRvMataUang: ArrayList<rvMataUang>
) : RecyclerView.Adapter<RvMataUangAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: RvMataUangBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(city: String, mCurrency: String, shortCurrency: String){
            binding.tvNameCity.text = city
            binding.tvCurrency.text = mCurrency
            binding.tvShortCurrency.text = shortCurrency
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = RvMataUangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listRvMataUang[position].country,listRvMataUang[position].currency,listRvMataUang[position].shortCurrency)
    }

    override fun getItemCount() = listRvMataUang.size


}